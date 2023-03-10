package com.q7g.judgehost.controller;

import com.q7g.judgehost.core.common.UnifiedResponse;
import com.q7g.judgehost.service.FileService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 判题相关文件处理相关接口
 */

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 下载某次提交的相关信息
     *
     * @param response     HttpServletResponse
     * @param submissionId 某次提交的id
     * @author yuzhanglong
     * @date 2020-8-17 20:48:17
     */
    @GetMapping("/submission/{submissionId}")
    public void downloadSubmissionById(@PathVariable String submissionId, HttpServletResponse response) {
        String zippedPath = fileService.getSubmissionDataById(submissionId);
        response.setContentType("application/octet-stream");
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + submissionId + ".zip");
        fileService.writeOutputStream(zippedPath, response);
    }

    /**
     * 删除所有用户提交的代码文件
     * 即 submission-path: 默认为/home/judgeEnvironment/submissions
     *
     * @author yuzhanglong
     * @date 2020-09-03 18:12:32
     */
    @DeleteMapping("/submission_path")
    private UnifiedResponse clearSubmissionFiles() {
        fileService.clearSubmissionPath();
        return new UnifiedResponse("删除成功");
    }

    /**
     * 删除所有用户提交的代码文件
     * 即 submission-path: 默认为/home/judgeEnvironment/submissions
     *
     * @author yuzhanglong
     * @date 2020-09-03 18:12:32
     */
    @DeleteMapping("/solution_path")
    private UnifiedResponse clearSolutionPath() {
        fileService.clearSolutionPath();
        return new UnifiedResponse("删除成功");
    }
}
