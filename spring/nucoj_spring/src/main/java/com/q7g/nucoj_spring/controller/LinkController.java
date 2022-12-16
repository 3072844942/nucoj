package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.LinkDTO;
import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.service.LinkService;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 友链模块
 */
@RestController
@Api(tags = "友链模块")
public class LinkController {
    @Autowired
    private LinkService linkService;

    /**
     * 获取全部链接
     * @return {@link Result<List<LinkDTO>> } 链接集合
     */
    @GetMapping("/link/all")
    @ApiOperation(value = "获取全部链接")
    public Result<PageDTO<LinkDTO>> getLinks() {
        return Result.ok(linkService.getAll());
    }

    /**
     * 更新链接
     * @param conditionVO JSON
     * @return
     */
    @PostMapping("/link/update")
    @ApiOperation(value = "更新链接集")
    public Result<?> updateLinks(@RequestBody ConditionVO conditionVO) {
        linkService.updateLinks(conditionVO.getList());
        return Result.ok();
    }

    /**
     * 删除链接
     * @param conditionVO 链接Id
     * @return
     */
    @PostMapping("/link/delete")
    @ApiOperation(value = "删除链接")
    public Result<?> deleteLink(@RequestBody ConditionVO conditionVO) {
        linkService.deleteLink(conditionVO.getId());
        return Result.ok();
    }
}
