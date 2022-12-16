package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.DiscussDTO;
import com.q7g.nucoj_spring.dto.PageDTO;

public interface DiscussService {

    /**
     * 获取满足条件的分享
     * @param current 页码
     * @param size 条数
     * @param keywords 搜索内容
     * @return 分线集
     */
    PageDTO<DiscussDTO> getDiscusses(int current, int size, String keywords, String userId);

    /**
     * 获取分享详情
     * @param discussId 分享ID
     * @return 分享信息
     */
    DiscussDTO getDiscuss(String discussId);

    /**
     * 更新分享
     * @param id id
     * @param title title
     * @param time  time
     * @param context   context
     * @param author    author
     */
    void updateDiscuss(String id, String title, Long time, String context, String author);

    /**
     * 删除分享
     * @param discussId 分享ID
     */
    void deleteDiscuss(String discussId);
}
