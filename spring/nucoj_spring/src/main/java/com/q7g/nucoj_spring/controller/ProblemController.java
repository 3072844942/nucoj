package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.ContestDTO;
import com.q7g.nucoj_spring.dto.JudgeStatusDTO;
import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.ProblemDTO;
import com.q7g.nucoj_spring.enums.ProblemStatusEnum;
import com.q7g.nucoj_spring.po.Problem;
import com.q7g.nucoj_spring.service.JudgeService;
import com.q7g.nucoj_spring.service.ProblemService;
import com.q7g.nucoj_spring.service.TagService;
import com.q7g.nucoj_spring.service.UserProblemService;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.ProblemVO;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题目模块
 */
@RestController
@Api(tags = "题目模块")
public class ProblemController {
    @Autowired
    private TagService tagService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private UserProblemService userProblemService;


    /**
     * 获取所有标签
     *
     * @return {@link Result<List<String>>} 标签集
     */
    @GetMapping("/problem/tags")
    @ApiOperation(value = "获取所有标签")
    public Result<List<String>> getTags() {
        return Result.ok(tagService.getTags());
    }

    /**
     * 获取所有等级
     *
     * @return {@link Result<List<String>>} 等级集
     */
    @GetMapping("/problem/grades")
    @ApiOperation(value = "获取所有等级")
    public Result<List<String>> getGrades() {
        return Result.ok(tagService.getGrades());
    }


    /**
     * 获取最近更新题目
     * @param size 条数
     * @return {@link Result<List<ProblemDTO>>} 题目集
     */
    @GetMapping("/problem/recent")
    @ApiOperation(value = "获取最近更新题目")
    public Result<List<ProblemDTO>> getRecentProblem(Integer size) {
        return Result.ok(problemService.getRecentProblem(size));
    }

    /**
     * 获取符合条件的题目
     *
     * @param condition 条件
     * @return {@link Result<List<ContestDTO>> } 题目集合
     */
    @PostMapping("/problem/list")
    @ApiOperation(value = "获取题目集")
    public Result<PageDTO<ProblemDTO>> getProblems(@RequestBody ConditionVO condition) {
        return Result.ok(
                problemService.getProblems(
                        condition.getCurrent(),
                        condition.getSize(),
                        condition.getKeywords(),
                        condition.getTags(),
                        false
                )
        );
    }

    /**
     * 后台获取题目集
     * @param condition page
     * @return
     */
    @PostMapping("/admin/problem/list")
    @ApiOperation(value = "后台获取题目集")
    public Result<PageDTO<ProblemDTO>> getAdminProblems(@RequestBody ConditionVO condition) {
        return Result.ok(
                problemService.getProblems(
                        condition.getCurrent(),
                        condition.getSize(),
                        condition.getKeywords(),
                        condition.getTags(),
                        true
                )
        );
    }

    /**
     *
     * @param conditionVO 题目Id
     * @return
     */
    @PostMapping("/problem/delete")
    @ApiOperation(value = "后台删除题目")
    public Result<?> deleteProblem(@RequestBody ConditionVO conditionVO) {
        problemService.deleteProblem(conditionVO.getId());
        return Result.ok();
    }

    /**
     *
     * @param problemVO 题目对象
     * @return
     */
    @PostMapping("/problem/update")
    @ApiOperation(value = "后台更新题目")
    public Result<?> updateProblem(@RequestBody ProblemVO problemVO) {
        problemService.updateProblem(Problem.of(problemVO));
        return Result.ok();
    }

    /**
     * 获取题目详情
     * @param problemId 题目ID
     * @return {@link Result<ProblemDTO>} 题目信息
     */
    @PostMapping("/problem/{problemId}")
    @ApiOperation(value = "获取题目详情")
    public Result<ProblemDTO> getProblem(@PathVariable String problemId) {
        return Result.ok(problemService.getProblem(problemId, false));
    }

    /**
     * 后台获取题目详情
     * @param conditionVO 题目Id
     * @return
     */
    @PostMapping("/admin/problem")
    @ApiOperation(value = "后台获取题目详情")
    public Result<ProblemDTO> getAdminProblem(@RequestBody ConditionVO conditionVO) {
        return Result.ok(problemService.getProblem(conditionVO.getId(), true));
    }

    /**
     * 调试代码
     * @param problem 提交的题目信息
     * @return 测试结果
     */
    @PostMapping("/problem/debug")
    @ApiOperation(value = "测试代码")
    public Result<String> debugProblem(@RequestBody ProblemVO problem) {
        return Result.ok(judgeService.debugCode(problem.getCode(), problem.getTest(), problem.getLanguage().getLanguage()));
    }

    /**
     * 提交题目
     * @param problem 提交的题目信息
     */
    @PostMapping("/problem/submit")
    @ApiOperation(value = "提交题目")
    public Result<JudgeStatusDTO> evaluationProblem(@RequestBody ProblemVO problem) {
        return Result.ok(problemService.judge(problem.getLanguage().getLanguage(), problem.getId(), problem.getUserId(), problem.getCode()));
    }

    /**
     * 获取用户完成的题目
     * @param userId 用户ID
     * @return 题目ID集
     */
    @GetMapping("/user/acceptProblem/{userId}")
    @ApiOperation(value = "获取用户完成的题目")
    public Result<?> getUserAcceptProblem(@PathVariable String userId) {
        return Result.ok(userProblemService.getUserAcceptProblem(userId));
    }

    /**
     * 获取用户尝试过的题目
     * @param userId 用户ID
     * @return 题目ID集
     */
    @GetMapping("/user/tryProblem/{userId}")
    @ApiOperation(value = "获取用户尝试过的题目")
    public Result<List<String>> getUserTryProblem(@PathVariable String userId) {
        return Result.ok(userProblemService.getUserTryProblem(userId));
    }

    /**
     *
     * @param problemId 题目ID
     * @return 题目状态
     */
    @GetMapping("/user/{userId}/problem/{problemId}")
    @ApiOperation(value = "获取题目的通过状态")
    public Result<ProblemStatusEnum> getUserProblemStatus(@PathVariable String userId, @PathVariable String problemId) {
        return Result.ok(userProblemService.getUserProblemStatus(userId, problemId));
    }
}
