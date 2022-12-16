package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.NoticeDTO;
import com.q7g.nucoj_spring.service.NoticeService;
import com.q7g.nucoj_spring.vo.ArticleVO;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告模块
 */
@RestController
@Api(tags = "公告模块")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 最近更新公告
     *
     * @return {@link Result<List<NoticeDTO>> } 公告集合
     */
    @GetMapping("/notice/recent")
    @ApiOperation(value = "获取最近公告")
    public Result<List<NoticeDTO>> getRecentNotice(@RequestParam Integer size) {
        return Result.ok(noticeService.getRecentNotice(size));
    }

    @PostMapping("/notice/list")
    @ApiOperation(value = "获取全部分享")
    public Result<PageDTO<NoticeDTO>> getNotices(@RequestBody ConditionVO condition) {
        return Result.ok(noticeService.getNotices(condition.getCurrent(), condition.getSize(), condition.getKeywords()));
    }

    /**
     * 公告详情
     *
     * @param noticeId 公告ID
     * @return {@link Result<NoticeDTO>} 公告详情
     */
    @GetMapping("/notice/{noticeId}")
    @ApiOperation(value = "获取公告详情")
    public Result<NoticeDTO> getNotice(@PathVariable String noticeId) {
        return Result.ok(noticeService.getNotice(noticeId));
    }

    /**
     * 后台更新公告
     * @param articleVO 公告对象
     * @return
     */
    @PostMapping("/notice/update")
    @ApiOperation(value = "更新公告")
    public Result<?> updateNotice(@RequestBody ArticleVO articleVO) {
        noticeService.updateNotice(articleVO.getId(), articleVO.getTitle(),
                articleVO.getTime(), articleVO.getContext(), articleVO.getAuthor());
        return Result.ok();
    }

    /**
     * 后台删除公告
     * @param conditionVO 公告ID
     * @return
     */
    @PostMapping("/notice/delete")
    @ApiOperation(value = "删除")
    public Result<?> deleteNotice(@RequestBody ConditionVO conditionVO) {
        noticeService.deleteNotice(conditionVO.getId());
        return Result.ok();
    }
}
