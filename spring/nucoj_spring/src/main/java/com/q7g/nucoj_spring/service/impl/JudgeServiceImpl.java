package com.q7g.nucoj_spring.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.q7g.nucoj_spring.dto.JudgeStatusDTO;
import com.q7g.nucoj_spring.enums.JudgeModel;
import com.q7g.nucoj_spring.enums.LanguageEnum;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.Judge;
import com.q7g.nucoj_spring.po.Problem;
import com.q7g.nucoj_spring.po.Result;
import com.q7g.nucoj_spring.repository.ProblemRepository;
import com.q7g.nucoj_spring.service.JudgeService;
import com.q7g.nucoj_spring.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.q7g.nucoj_spring.enums.FilePathEnum.*;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${oj.judgeAddress}")
    private String judgeAddress;

    /**
     * 本地路径
     */
    @Value("${upload.local.path}")
    private String localPath;

    /**
     * 访问url
     */
    @Value("${upload.local.url}")
    private String localUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String debugCode(String code, String test, String language) {
        String fileName = String.valueOf(System.currentTimeMillis());
        // 写入文件
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(localPath + DEBUG.getPath() + fileName));
            writer.write(test);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 类似于提交代码， 不给期待输出
        // 将参数存入map
        List<Map<String, String>> solution = new LinkedList<>();
        Map<String, String> map = new HashMap<>();
        map.put("stdIn", localUrl + DEBUG.getPath() + fileName);
        map.put("expectedStdOut", localUrl + DEBUG.getPath() + fileName);
        solution.add(map);

        Map<String, Object> judge = new HashMap<>();
        judge.put("language", language);
        judge.put("solutions", solution);
        judge.put("submissionCode", code);
        judge.put("judgePreference", JudgeModel.OI.getMessage());
        judge.put("memoryLimit", 65536);
        judge.put("outputLimit", 10000);
        // 发送 Judge 到 localhost：8080/judge/debug
        Result result = restTemplate.postForObject( "https://JUDGE/judge/result", HttpUtil.mapMapToPostJson(judge), Result.class);
        // 返回Result
        if (result.getJudgeResults().size() == 0) {
            String extro = "";
            for (String i : result.getExtraInfo()) {
                extro += i + "\n";
            }
            throw new BizException(extro);
        }
        String stdOutPath = result.getJudgeResults().get(0).getStdOutPath();
        StringBuffer answer = new StringBuffer();
        try {
            FileReader reader = new FileReader(stdOutPath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line ;
            while ((line = bufferedReader.readLine()) != null) {
                answer.append(line);
            }
        } catch (Exception e) {
            throw new BizException("系统错误");
        }

        return answer.toString();
    }


    @Override
    public JudgeStatusDTO judge(Problem problem, String language, String code, JudgeModel judgeModel) {
        // 将参数存入map
        Map<String, Object> judge = new HashMap<>();
        judge.put("language", language);
        judge.put("solutions", getSolutions(problem.getRecordId()));
        judge.put("submissionCode", code);
        judge.put("judgePreference", judgeModel.getMessage());
        judge.put("memoryLimit", problem.getMemoryRequire());
        judge.put("outputLimit", problem.getTimeRequire());


        Result result = null;
        if (problem.getIsSpecial()) {
            judge.put("special", problem.getSpecialJudge());
            judge.put("specialLanguage", problem.getSpecialLanguage());
            // 发送 Judge 到 localhost：8080/judge/special
            result = restTemplate.postForObject("https://JUDGE/judge/special", HttpUtil.mapMapToPostJson(judge), Result.class);
        }
        else {
            // 发送 Judge 到 localhost：8080/judge/result
            result = restTemplate.postForObject(  "https://JUDGE/judge/result", HttpUtil.mapMapToPostJson(judge), Result.class);
        }


        // ? 要不要交换机
        // 记录到用户信息
        // 记录提交记录
        // 返回结果
        return JudgeStatusDTO.of(result);
    }

    /**
     * 获取所有输入输出
     *
     * @param url 题目地址
     * @return
     */
    private List<Map<String, String>> getSolutions(String url) {
        int count = 1;
        List<Map<String, String>> list = new LinkedList<>();
        File dest = new File(localPath + JUDGE.getPath() + url + "/", count + ".in");
        while (dest.exists()) {
            // 如果输入文件存在
            Map<String, String> map = new HashMap<>();
            map.put("stdIn", localUrl + JUDGE.getPath() + url + "/" + count + ".in");
            map.put("expectedStdOut", localUrl + JUDGE.getPath() + url + "/" + count + ".out");
            list.add(map);

            // 计数++， 更新下一个文件
            count++;
            dest = new File(localPath + JUDGE.getPath() + url + "/" + count + ".in");
        }
        return list;
    }
}
