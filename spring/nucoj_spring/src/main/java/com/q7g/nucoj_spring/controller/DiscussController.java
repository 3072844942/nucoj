package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.ContestDTO;
import com.q7g.nucoj_spring.dto.DiscussDTO;
import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.service.DiscussService;
import com.q7g.nucoj_spring.vo.ArticleVO;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分享模块
 */
@RestController
@Api(tags = "分享模块")
public class DiscussController {
    @Autowired
    private DiscussService discussService;

    /**
     * 获取符合条件的分享
     *
     * @param condition 条件
     * @return {@link Result<List<ContestDTO>> } 分享集合
     */
    @PostMapping("/discuss/list")
    @ApiOperation(value = "获取分享集")
    public Result<PageDTO<DiscussDTO>> getDiscusses(@RequestBody ConditionVO condition) {
        return Result.ok(discussService.getDiscusses(condition.getCurrent(), condition.getSize(), condition.getKeywords(), condition.getId()));
    }

    /**
     * 获取分享详情
     * @param discussId 分享ID
     * @return {@link Result<DiscussDTO>} 分享信息
     */
    @GetMapping("/discuss/{discussId}")
    @ApiOperation(value = "获取分享详情")
    public Result<DiscussDTO> getDiscuss(@PathVariable String discussId) {
        return Result.ok(discussService.getDiscuss(discussId));
    }

    /**
     * 更新分享
     * @param articleVO 文章对象
     * @return
     */
    @PostMapping("/discuss/update")
    @ApiOperation(value = "更新分享")
    public Result<?> updateDisucss(@RequestBody ArticleVO articleVO) {
        discussService.updateDiscuss(articleVO.getId(), articleVO.getTitle(),
                articleVO.getTime(), articleVO.getContext(), articleVO.getAuthor());
        return Result.ok();
    }

    /**
     * 删除分享
     * @param conditionVO 分享Id
     * @return
     */
    @PostMapping("/discuss/delete")
    @ApiOperation(value = "删除分享")
    public Result<?> deleteDiscuss(@RequestBody ConditionVO conditionVO) {
        discussService.deleteDiscuss(conditionVO.getId());
        return Result.ok();
    }
}
