package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.NoticeDTO;

import java.util.List;

/**
 * 公告服务
 */
public interface NoticeService {
    /**
     * 获取最近公告
     * @param size 条数
     * @return 公告集
     */
    List<NoticeDTO> getRecentNotice(int size);

    /**
     * 获取公告详情
     * @param noticeId 公告ID
     * @return 公告信息
     */
    NoticeDTO getNotice(String noticeId);

    /**
     * 更新公告
     * @param id id
     * @param title title
     * @param time time
     * @param context context
     * @param author author
     */
    void updateNotice(String id, String title, Long time, String context, String author);

    /**
     * 获取公告集
     * @param current 页码
     * @param size 条数
     * @param keywords 搜索内容
     * @return
     */
    PageDTO<NoticeDTO> getNotices(int current, int size, String keywords);

    /**
     * 删除公告
     * @param noticeId 公告ID
     */
    void deleteNotice(String noticeId);
}
