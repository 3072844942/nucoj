package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.*;
import com.q7g.nucoj_spring.po.Contest;
import com.q7g.nucoj_spring.po.Problem;
import com.q7g.nucoj_spring.po.Submit;
import com.q7g.nucoj_spring.service.ContestService;
import com.q7g.nucoj_spring.service.UserInfoService;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.ContestVO;
import com.q7g.nucoj_spring.vo.ProblemVO;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 比赛模块
 */
@RestController
@Api(tags = "比赛模块")
public class ContestController {

    @Autowired
    private ContestService contestService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取最近更新的比赛
     *
     * @return {@link Result<ContestDTO> } 比赛信息
     */
    @GetMapping("/contest/recent")
    @ApiOperation(value = "获取最近比赛")
    public Result<List<ContestDTO>> getRecentContest(@RequestParam int size) {
        return Result.ok(contestService.getRecentContest(size));
    }

    /**
     * 获取符合条件的比赛
     * @param condition 条件
     * @return {@link Result<List<ContestDTO>>} 比赛集
     */
    @PostMapping("/contest/list")
    @ApiOperation(value = "获取比赛集")
    public Result<PageDTO<ContestDTO>> getContests(@RequestBody ConditionVO condition) {
        return Result.ok(contestService.getContests(condition.getCurrent(), condition.getSize(), condition.getKeywords()));
    }

    /**
     * 获取比赛详情
     * @param contestId 比赛ID
     * @return 比赛信息
     */
    @PostMapping("/contest/{contestId}")
    @ApiOperation(value = "获取比赛详情")
    public Result<ContestDTO> getContest(@RequestBody ConditionVO conditionVO, @PathVariable String contestId) {
        return Result.ok(contestService.getContest(contestId,  false, conditionVO.getId()));
    }

    /**
     * 获取比赛详情
     * @param contestId 比赛ID
     * @return 比赛信息
     */
    @PostMapping("/contest/{contestId}/problem/{problemId}")
    @ApiOperation(value = "获取比赛详情")
    public Result<ProblemDTO> getContestProblem(@PathVariable String contestId, @PathVariable String problemId) {
        return Result.ok(contestService.getContestProblem(contestId,  problemId));
    }

    /**
     * 后台获取比赛详情
     * @param conditionVO 条件
     * @return
     */
    @PostMapping("/admin/contest")
    @ApiOperation(value = "后台获取比赛详情")
    public Result<ContestDTO> getAdminContest(@RequestBody ConditionVO conditionVO) {
        return Result.ok(contestService.getContest(conditionVO.getId(), true, ""));
    }

    /**
     * 后台更新比赛
     * @param contestVO 比赛对象
     * @return
     */
    @PostMapping("/contest/update")
    @ApiOperation(value = "后台更新比赛")
    public Result<?> updateContest(@RequestBody ContestVO contestVO) {
        contestService.updateContest(Contest.of(contestVO));
        return Result.ok();
    }

    /**
     * 后台删除比赛
     * @param conditionVO 比赛Id
     * @return
     */
    @PostMapping("/contest/delete")
    @ApiOperation(value = "后台删除比赛")
    public Result<?> deleteContest(@RequestBody ConditionVO conditionVO) {
        contestService.deleteContest(conditionVO.getId());
        return Result.ok();
    }

    /**
     *
     * @param conditionVO 比赛Id，题目Id
     * @return
     */
    @PostMapping("/admin/contest/problem")
    @ApiOperation(value = "后台获取比赛题目详情")
    public Result<ProblemDTO> getAdminContestProblem(@RequestBody ConditionVO conditionVO) {
        return Result.ok(contestService.getAdminContestProblem(conditionVO.getContestId(), conditionVO.getId()));
    }

    /**
     * 后台更新比赛题目
     * @param problemVO 比赛Id， 题目对象
     * @return
     */
    @PostMapping("/contest/problem/update")
    @ApiOperation(value = "后台更新比赛题目")
    public Result<?> updateContestProblem(@RequestBody ProblemVO problemVO){
        contestService.updateContestProblem(problemVO.getContestId(), Problem.of(problemVO));
        return Result.ok();
    }

    /**
     * 后台删除比赛题目
     * @param conditionVO 比赛Id， 题目Id
     * @return
     */
    @PostMapping("/contest/problem/delete")
    @ApiOperation(value = "后台删除比赛题目")
    public Result<?> deleteContestProblem(@RequestBody ConditionVO conditionVO) {
        contestService.deleteContestProblem(conditionVO.getContestId(), conditionVO.getId());
        return Result.ok();
    }

    /**
     * 获取用户参加的比赛Id集
     * @param conditionVO
     * @return
     */
    @PostMapping("/contest/entry/user")
    @ApiOperation(value = "获取用户参加的比赛Id集")
    public Result<List<String>> getUserContest(@RequestBody ConditionVO conditionVO) {
        return Result.ok(userInfoService.getUserContest(conditionVO.getId()));
    }

    /**
     * 后台获取报名比赛的用户集
     * @param conditionVO 比赛Id， page
     * @return
     */
    @PostMapping("/contest/entry/user/list")
    @ApiOperation(value = "后台获取报名比赛的用户集")
    public Result<PageDTO<UserDetailDTO>> getContestUser(@RequestBody ConditionVO conditionVO) {
        return Result.ok(contestService.getContestUser(conditionVO.getContestId(), conditionVO.getCurrent(), conditionVO.getSize()));
    }

    /**
     * 加入比赛
     * @param conditionVO 用户Id， 比赛Id
     * @return
     */
    @PostMapping("/contest/entry")
    @ApiOperation(value = "加入比赛")
    public Result<?> entryContest(@RequestBody ConditionVO conditionVO) {
        userInfoService.entryContest(conditionVO.getId(), conditionVO.getContestId());
        return Result.ok();
    }

    @PostMapping("/contest/problem/submit")
    public Result<JudgeStatusDTO> submit(@RequestBody ProblemVO problem) {
        return Result.ok(contestService.judge(problem.getContestId(), problem.getLanguage().getLanguage(), problem.getId(), problem.getUserId(), problem.getCode()));
    }

    @PostMapping("/contest/rank")
    public Result<ContestRankDTO> getContestRank(@RequestBody ConditionVO conditionVO) {
        return Result.ok(contestService.getContestRank(conditionVO.getId(), conditionVO.getCurrent(), conditionVO.getSize(), conditionVO.getUserId()));
    }

    @PostMapping("/contest/state/user")
    public Result<ContestRankUserDTO> getUserContestState(@RequestBody ConditionVO conditionVO) {
        return Result.ok(contestService.getUserContestState(conditionVO.getId(), conditionVO.getContestId()));
    }

    @PostMapping("/contest/submit/record")
    public Result<PageDTO<ContestSubmitDTO>> getContestSubmits(@RequestBody ConditionVO conditionVO) {
        return Result.ok(contestService.getContestSubmits(conditionVO.getId(), conditionVO.getCurrent(), conditionVO.getSize()));
    }
}
