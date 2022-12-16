package com.q7g.judgehost.controller;

import com.q7g.judgehost.exception.http.ForbiddenException;
import com.q7g.judgehost.dto.JudgeDTO;
import com.q7g.judgehost.service.JudgeService;
import com.q7g.judgehost.vo.JudgeConditionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;

/**
 * 判题接口
 */
@RestController
@RequestMapping("/judge")

public class JudgeController {
    private final JudgeService judgeService;

    @Autowired
    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    /**
     * 执行判题
     *
     * @param judgeDTO 判题相关数据传输对象
     * @author yuzhanglong
     * @date 2020-7-1 21:00
     */
    @PostMapping("/result")
    public JudgeConditionVO runJudge(@RequestBody @Validated JudgeDTO judgeDTO) throws ExecutionException, InterruptedException {
        CompletableFuture<JudgeConditionVO> judgeResults;
        try {
            judgeResults = judgeService.runJudge(judgeDTO);
        } catch (RejectedExecutionException e) {
            throw new ForbiddenException("B1005");
        }
        return judgeResults.get();
    }

    @PostMapping("/special")
    public JudgeConditionVO specialJudge(@RequestBody @Validated JudgeDTO judgeDTO) throws ExecutionException, InterruptedException {
        CompletableFuture<JudgeConditionVO> judgeResults;
        try {
            judgeResults = judgeService.specialJudge(judgeDTO);
        } catch (RejectedExecutionException e) {
            throw new ForbiddenException("B1005");
        }
        return judgeResults.get();
    }
}