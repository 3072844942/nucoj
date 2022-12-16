package com.q7g.judgehost.service;

import com.alibaba.fastjson.JSON;
import com.q7g.judgehost.bo.JudgeConfigurationBO;
import com.q7g.judgehost.core.configuration.JudgeEnvironmentConfiguration;
import com.q7g.judgehost.core.enumeration.JudgeResultEnum;
import com.q7g.judgehost.core.enumeration.LanguageScriptEnum;
import com.q7g.judgehost.dto.JudgeDTO;
import com.q7g.judgehost.dto.SingleJudgeResultDTO;
import com.q7g.judgehost.dto.SolutionDTO;
import com.q7g.judgehost.exception.http.NotFoundException;
import com.q7g.judgehost.network.HttpRequest;
import com.q7g.judgehost.store.redis.JudgeCache;
import com.q7g.judgehost.utils.DataTransform;
import com.q7g.judgehost.utils.FileUtil;
import com.q7g.judgehost.utils.HttpUtil;
import com.q7g.judgehost.utils.JudgeHolder;
import com.q7g.judgehost.vo.JudgeConditionVO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.q7g.judgehost.core.enumeration.JudgeResultEnum.ACCEPTED;
import static com.q7g.judgehost.core.enumeration.JudgeResultEnum.WRONG_ANSWER;

/**
 * 判题服务模块
 *
 * @author yuzhanglong
 * @date 2020-6-24 12:10:43
 */

@Service
public class JudgeService {
    private final JudgeEnvironmentConfiguration judgeEnvironmentConfiguration;
    private final JudgeCache judgeCache;
    public static final String SOLUTION_STD_IN_PATH_KEY = "stdIn";
    public static final String SOLUTION_EXPECTED_STD_OUT_PATH_KEY = "expectedStdOut";
    public static final int ENABLE_JUDGE_CORE_GUARD = 1;
    public static final int DISABLE_JUDGE_CORE_GUARD = 0;
    public static final int USE_ROOT_UID = 0;
    public static final int USE_DEFAULT_UID = 6666;
    public static final int COMPILE_OUT_MAX_SIZE = 100000;

    @Autowired
    private RestTemplate restTemplate;


    public JudgeService(JudgeEnvironmentConfiguration judgeEnvironmentConfiguration, JudgeCache judgeCache) {
        this.judgeEnvironmentConfiguration = judgeEnvironmentConfiguration;
        this.judgeCache = judgeCache;
    }

    /**
     * 调用compile.sh 生成脚本
     *
     * @return String 编译返回的信息，如果没有信息，则编译成功
     * @author yuzhanglong
     * @date 2020-6-24 12:10:43
     */
    private List<String> compileSubmission() {
        // 编译脚本
        String compileScript = JudgeHolder.getCompileScriptPath();

        // 本次提交工作目录
        String submissionWorkingPath = JudgeHolder.getSubmissionWorkingPath();

        // 判题核心脚本
        String judgeCoreScript = JudgeHolder.getJudgeCoreScriptPath();
        if (!FileUtil.isFileIn(compileScript)) {
            throw new NotFoundException("B1002");
        }

        JudgeDTO judgeDTO = JudgeHolder.getJudgeConfig();
        // 获取编程语言
        LanguageScriptEnum language = LanguageScriptEnum.toLanguageType(judgeDTO.getLanguage());

        // 用户代码
        String codePath = JudgeHolder.getCodePath(language.getExtensionName());

        // 对应语言的编译脚本
        String buildScript = language.getBuildScriptByRunningPath(submissionWorkingPath, codePath);

        // 执行编译脚本
        try {
            Process process = JudgeHolder.getRunner().exec(
                    new String[]{
                            compileScript,
                            submissionWorkingPath,
                            codePath,
                            judgeDTO.getSubmissionCode(),
                            buildScript,
                            judgeCoreScript,
                            String.valueOf(language == LanguageScriptEnum.JAVA ? USE_ROOT_UID : USE_DEFAULT_UID),
                            String.valueOf(COMPILE_OUT_MAX_SIZE)
                    });
            process.waitFor();
        } catch (IOException | InterruptedException ioException) {
            // TODO：异常处理
            ioException.printStackTrace();
        }
        return readFile(submissionWorkingPath + "/" + JudgeHolder.COMPILE_STD_ERR_NAME);
    }

    /**
     * 执行判题
     *
     * @param judgeDTO judgeDTO对象
     * @return CompletableFuture<List < SingleJudgeResultDTO>>由一个或多个单次判题结果组成的list，以CompletableFuture包装
     * @author yuzhanglong
     * @date 2020-6-27 12:21:43
     */
    @Async(value = "judgeHostServiceExecutor")
    public CompletableFuture<JudgeConditionVO> runJudge(JudgeDTO judgeDTO) {

        JudgeConfigurationBO judgeConfigurationBO = new JudgeConfigurationBO(
                judgeDTO,
                judgeEnvironmentConfiguration.getSubmissionPath(),
                judgeEnvironmentConfiguration.getScriptPath(),
                judgeEnvironmentConfiguration.getResolutionPath()
        );
        JudgeHolder.initJudgeConfiguration(judgeConfigurationBO);

        // 编译用户的提交
        List<String> compileResult = compileSubmission();
        JudgeHolder.setExtraInfo(compileResult);
        List<SingleJudgeResultDTO> result = new ArrayList<>();
        // 编译阶段成功，开始运行用户代码
        if (isCompileSuccess(compileResult)) {
            List<SolutionDTO> totalResolution = judgeDTO.getSolutions();
            int solutionIndex = 0;
            for (SolutionDTO solutionDTO : totalResolution) {
                SingleJudgeResultDTO singleJudgeResult = runForSingleJudge(solutionDTO, solutionIndex);
                boolean isAccept = singleJudgeResult.getCondition().equals(ACCEPTED.getNumber());
                // 这个测试点没有通过，并且是acm模式
                result.add(singleJudgeResult);
                if (!isAccept && judgeDTO.isAcmMode()) {
                    break;
                }
                // oi模式，继续执行判题
                solutionIndex++;
            }
        } else {
            SingleJudgeResultDTO resolution = new SingleJudgeResultDTO();
            resolution.setCondition(JudgeResultEnum.COMPILE_ERROR.getNumber());
            resolution.setMessageWithCondition();
            result.add(resolution);
        }
        return CompletableFuture.completedFuture(new JudgeConditionVO(result, JudgeHolder.getExtraInfo(), JudgeHolder.getSubmissionId()));
    }

    /**
     * 执行特别判题
     *
     * @param judgeDTO judgeDTO对象
     * @return CompletableFuture<List < SingleJudgeResultDTO>>由一个或多个单次判题结果组成的list，以CompletableFuture包装
     * @author yuzhanglong
     * @date 2020-6-27 12:21:43
     */
    @Async(value = "judgeHostServiceExecutor")
    public CompletableFuture<JudgeConditionVO> specialJudge(JudgeDTO judgeDTO) {
        Map<String, Object> judge = new HashMap<>();
        judge.put("language", judgeDTO.getLanguage());
        judge.put("solutions", judgeDTO.getSolutions());
        judge.put("submissionCode", judgeDTO.getSubmissionCode());
        judge.put("judgePreference", "OI");
        judge.put("memoryLimit", judgeDTO.getMemoryLimit());
        judge.put("outputLimit", judgeDTO.getOutputLimit());

        // 发送 Judge 到 localhost：8080/judge/result
        JudgeConditionVO userJudge =
                restTemplate.postForObject("http://JUDGE/judge/result", HttpUtil.mapMapToPostJson(judge), JudgeConditionVO.class);
        // 如果存一个节点没有被正常运行 - condition 不为0/1 =》 不为0/1的节点数量不为0
        List<SingleJudgeResultDTO> collect = userJudge.getJudgeResults().stream().filter(item ->
                !item.getCondition().equals(ACCEPTED.getNumber()) && !item.getCondition().equals(WRONG_ANSWER.getNumber())).collect(Collectors.toList());
        if (collect.size() != 0) {
            // 返回所有错误节点
            return CompletableFuture.completedFuture(new JudgeConditionVO(collect, userJudge.getExtraInfo(), userJudge.getSubmissionId()));
        }

        // 将special设置为主代码
        judgeDTO.setLanguage(judgeDTO.getSpecialLanguage());
        judgeDTO.setSubmissionCode(judgeDTO.getSpecial());
        // 初始化
        JudgeConfigurationBO judgeConfigurationBO = new JudgeConfigurationBO(
                judgeDTO,
                judgeEnvironmentConfiguration.getSubmissionPath(),
                judgeEnvironmentConfiguration.getScriptPath(),
                judgeEnvironmentConfiguration.getResolutionPath()
        );
        JudgeHolder.initJudgeConfiguration(judgeConfigurationBO);

        // 编译special
        compileSubmission();
        List<SingleJudgeResultDTO> result = new ArrayList<>();
        // 编译阶段成功，开始运行用户代码
        // 这里不会编辑失败， 因为加入special时必须是通过的代码
        List<SingleJudgeResultDTO> judgeResults = userJudge.getJudgeResults();
        // 遍历用户的判题结果
        for (SingleJudgeResultDTO i : judgeResults) {
            Process process = null;
            int exitCode;
            try {
                // 使用命令行参数 后面拼接两个参数为 标准输入地址和 用户输出地址
                ProcessBuilder builder = new ProcessBuilder(Arrays.asList(
                        JudgeHolder.getRunnerScriptPath(),
                        i.getStdInPath(),
                        i.getStdOutPath()
                ));

                process = builder.start();
                process.waitFor(10, TimeUnit.SECONDS);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                exitCode = 8;
            }
            // 获取返回值
            if (Objects.isNull(process)) exitCode = 8;
            else exitCode = process.exitValue();
            result.add(i);
            // 返回值为0则为AC
            if (exitCode == 0) {
                i.setCondition(0);
                i.setMessage("ACCEPT");
            }
            // 否则可能有各种情况， 但是统一为WA，
            else { // wa？ pe？ tie？
                i.setCondition(1);
                i.setMessage("WRONG_ANSWER");
                // 如果时ACM模式
                if (judgeDTO.isAcmMode()) break;
            }
        }

        return CompletableFuture.completedFuture(new JudgeConditionVO(result, userJudge.getExtraInfo(), userJudge.getSubmissionId()));
    }


    /**
     * 传入编译结果，根据语言特性来判断编译是否成功
     *
     * @param compileResult 编译结果
     * @return Boolean 编译是否成功
     * @author yuzhanglong
     * @date 2020-6-30 21:21
     */
    private Boolean isCompileSuccess(List<String> compileResult) {
        LanguageScriptEnum language = LanguageScriptEnum.toLanguageType(JudgeHolder.getJudgeConfig().getLanguage());
        // c语言家族（c && cpp）
        boolean isCppFamily = (language == LanguageScriptEnum.C || language == LanguageScriptEnum.C_PLUS_PLUS);
        // java
        boolean isJava = (language == LanguageScriptEnum.JAVA);
        // 另外，python 属于解释性语言，不在此处考虑
        for (String str : compileResult) {
            boolean isBad = str.contains("error:") || str.contains("错误：") || str.contains("Error:");
            if (isCppFamily && isBad) {
                return false;
            }
            if (isJava && isBad) {
                return false;
            }
        }
        return true;
    }

    /**
     * 读取某个文件的内容
     *
     * @param filePath 文件路径
     * @return List<String> 错误内容，我们用数组存储，用下标来代表行
     * @author yuzhanglong
     * @date 2020-7-1 9:22
     */
    private List<String> readFile(String filePath) {
        List<String> judgeErrors = null;
        try {
            judgeErrors = FileUtil.readFileByLines(filePath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return judgeErrors;
    }

    /**
     * 根据期望数据来执行单次判题
     *
     * @param singleResolution 用户传入的单次判题的正确解决方案，参见ResolutionDTO类
     * @param index            第index个测试点
     * @return SingleJudgeResultDTO 单次判题结果
     * @author yuzhanglong
     * @date 2020-7-1 9:47
     * @see SolutionDTO
     */
    private SingleJudgeResultDTO runForSingleJudge(SolutionDTO singleResolution, Integer index) {
        String singleJudgeRunningName = "running_" + index.toString();
        Map<String, String> resolution = getResolutionInputAndOutputFile(singleResolution);
        SingleJudgeResultDTO singleJudgeResult = startJudging(resolution.get(SOLUTION_STD_IN_PATH_KEY), singleJudgeRunningName);
        List<String> judgeCoreStdErr = readFile(singleJudgeResult.getStdErrPath());

        // 没有stderr输出时
        if (judgeCoreStdErr.size() == 0) {
            Boolean isRunSuccess = singleJudgeResult.getCondition() == 1;
            // 对比
            Boolean isPass = compareOutputWithResolutions(singleJudgeResult.getStdOutPath(), resolution.get(SOLUTION_EXPECTED_STD_OUT_PATH_KEY));
            // 如果通过，将condition设置为 0
            if (isPass && isRunSuccess) {
                singleJudgeResult.setCondition(ACCEPTED.getNumber());
            }
        } else {
            JudgeHolder.setExtraInfo(judgeCoreStdErr);
            singleJudgeResult.setCondition(JudgeResultEnum.RUNTIME_ERROR.getNumber());
        }
        singleJudgeResult.setMessageWithCondition();
        return singleJudgeResult;
    }

    /**
     * 获取输入文件和期望的输出文件，供后续判题使用
     *
     * @param resolution 解决方案数据传输对象
     * @return 保存了输入文件、输出文件本地地址的hashMap
     * @author yuzhanglong
     * @date 2020-6-27 12:21:43
     */
    private Map<String, String> getResolutionInputAndOutputFile(SolutionDTO resolution) {
        // 获取远程地址
        String inputFile = resolution.getStdIn();
        String outputFile = resolution.getExpectedStdOut();

        // 从缓存中查询是否已经有相关的本地路径
        Map<String, String> cachePathReflect = judgeCache.getSolutionLocalPathByRemoteUrl(inputFile);
        if (cachePathReflect == null) {
            // 不存在，我们从远程资源服务器下载、获取输入和期望输出
            Resource inputFileResource = HttpRequest.getFile(inputFile);
            Resource outputFileResource = HttpRequest.getFile(outputFile);
            UUID p = UUID.randomUUID();
            String inPath = JudgeHolder.getResolutionPath() + "/" + p + "/" + "solution.in";
            String outPath = JudgeHolder.getResolutionPath() + "/" + p + "/" + "solution.out";

            try {
                File inFile = new File(inPath);
                FileUtils.copyInputStreamToFile(inputFileResource.getInputStream(), inFile);
                File outFile = new File(outPath);
                FileUtils.copyInputStreamToFile(outputFileResource.getInputStream(), outFile);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            // 保存它们
            Map<String, String> map = new HashMap<>(2);
            map.put(SOLUTION_STD_IN_PATH_KEY, inPath);
            map.put(SOLUTION_EXPECTED_STD_OUT_PATH_KEY, outPath);
            // 使用redis缓存url到本地路径的映射, 减少请求次数
            judgeCache.addSolutionRemoteUrlToLocalMap(inputFile, map);
            return map;
        }
        boolean isStdInHave = FileUtil.isFileIn(cachePathReflect.get(SOLUTION_STD_IN_PATH_KEY));
        if (isStdInHave) {
            // 如果本地的输入存在（输入输出要么共存要么同时不在，只判断一个即可）
            return cachePathReflect;
        }
        judgeCache.removeRemotePathToLocalPathMapItem(inputFile);
        return getResolutionInputAndOutputFile(resolution);
    }


    /**
     * 比较用户输出和期望输出
     *
     * @param submissionOutput 用户提交的输出文件路径
     * @param expectedOutput   期望输出文件路径
     * @return Boolean 输出是否相同
     * @author yuzhanglong
     * @date 2020-6-24 12:20:43
     */

    private Boolean compareOutputWithResolutions(String submissionOutput, String expectedOutput) {
        String exitCode = "0";
        try {
            String compareScript = JudgeHolder.getCompareScriptPath();

            Process process = JudgeHolder.getRunner().exec(new String[]{
                    compareScript,
                    submissionOutput,
                    expectedOutput
            });
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            exitCode = reader.readLine();
        } catch (IOException | InterruptedException ioException) {
            // TODO：异常处理
            ioException.printStackTrace();
        }
        return "0".equals(exitCode);
    }

    /**
     * 调用判题核心，执行判题
     *
     * @param stdInPath 单个输入文件
     * @param name      本次测试点文件名称
     * @return SingleJudgeResultDTO 单次判题结果
     * @author yuzhanglong
     * @date 2020-6-24 12:10:43
     */
    private SingleJudgeResultDTO startJudging(String stdInPath, String name) {
        String judgeCoreScript = JudgeHolder.getJudgeCoreScriptPath();
        JudgeDTO config = JudgeHolder.getJudgeConfig();
        String workingPath = JudgeHolder.getSubmissionWorkingPath();
        LanguageScriptEnum language = LanguageScriptEnum.toLanguageType(JudgeHolder.getJudgeConfig().getLanguage());
        // c语言家族（c && cpp）
        boolean isCppFamily = (language == LanguageScriptEnum.C || language == LanguageScriptEnum.C_PLUS_PLUS);
        String[] command = {
                judgeCoreScript,
                "-r", JudgeHolder.getRunnerScriptPath(),
                "-o", workingPath + "/" + name + ".out",
                "-t", String.valueOf(config.getRealTimeLimit()),
                "-c", String.valueOf(config.getCpuTimeLimit()),
                "-m", String.valueOf(config.getMemoryLimit()),
                "-f", String.valueOf(config.getOutputLimit()),
                "-e", workingPath + "/" + name + ".err",
                "-i", stdInPath,
                "-g", String.valueOf(isCppFamily ? ENABLE_JUDGE_CORE_GUARD : DISABLE_JUDGE_CORE_GUARD)
        };
        List<String> result = new ArrayList<>();
        try {
            Process process = JudgeHolder.getRunner().exec(command);
            process.waitFor();
            result = readStdout(process);
        } catch (IOException | InterruptedException ioException) {
            // TODO：异常处理
            ioException.printStackTrace();
        }
        // 将判题核心的stdout转换成数据传输对象
        return JSON.parseObject(DataTransform.stringListToString(result), SingleJudgeResultDTO.class);
    }

    /**
     * 获取运行的脚本/可执行文件的输出
     *
     * @param process 运行的进程对象
     * @return String 进程输出
     * @throws IOException an I/O exception
     * @author yuzhanglong
     * @date 2020-6-30 21:21
     */
    private List<String> readStdout(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> stringList = new ArrayList<>();
        String temp;
        while ((temp = reader.readLine()) != null) {
            stringList.add(temp);
        }
        BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((temp = errReader.readLine()) != null) {
            stringList.add(temp);
        }
        return stringList;
    }

    /**
     * 将用户代码写到相应工作目录中
     *
     * @param code      代码
     * @param extension 代码扩展名
     * @author yuzhanglong
     * @date 2020-9-8 01:08:20
     */
    private Boolean writeCodeToWorkingPath(String code, String extension) {
        String path = JudgeHolder.getCodePath(extension);
        return FileUtil.writeDataToFilePath(code, new File(path));
    }
}