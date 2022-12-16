package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.SolutionDTO;
import com.q7g.nucoj_spring.service.SolutionService;
import com.q7g.nucoj_spring.vo.ArticleVO;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题解模块
 */
@RestController
@Api(tags = "题解模块")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;
    /**
     * 获取符合条件的题解
     *
     * @param condition 条件
     * @return {@link Result<List<SolutionDTO>> } 题解集合
     */
    @PostMapping("/solution/list")
    @ApiOperation(value = "获取题解集")
    public Result<PageDTO<SolutionDTO>> getSolutions(@RequestBody ConditionVO condition) {
        return Result.ok(solutionService.getSolutions(condition.getCurrent(), condition.getSize(), condition.getKeywords(), condition.getId()));
    }

    /**
     * 获取题解详情
     * @param solutionId 题解ID
     * @return {@link Result<SolutionDTO>} 题解详情
     */
    @GetMapping("/solution/{solutionId}")
    @ApiOperation(value = "获取题解详情")
    public Result<SolutionDTO> getSolution(@PathVariable String solutionId) {
        return Result.ok(solutionService.getSolution(solutionId));
    }

    /**
     * 后台更新题解
     * @param articleVO 文章对象
     * @return
     */
    @PostMapping("/solution/update")
    @ApiOperation(value = "后台更新题解")
    public Result<?> updateNotice(@RequestBody ArticleVO articleVO) {
        solutionService.updateSolution(articleVO.getId(), articleVO.getTitle(),
                articleVO.getTime(), articleVO.getContext(), articleVO.getAuthor());
        return Result.ok();
    }

    /**
     * 后台删除题解
     * @param conditionVO 题解Id
     * @return
     */
    @PostMapping("/solution/delete")
    @ApiOperation(value = "删除")
    public Result<?> deleteSolution(@RequestBody ConditionVO conditionVO) {
        solutionService.deleteSolution(conditionVO.getId());
        return Result.ok();
    }
}
