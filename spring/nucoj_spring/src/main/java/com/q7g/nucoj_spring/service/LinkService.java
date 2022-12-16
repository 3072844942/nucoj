package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.LinkDTO;
import com.q7g.nucoj_spring.dto.PageDTO;

import java.util.List;

/**
 * 友情链接服务
 */
public interface LinkService {
    /**
     * 过去全部链接
     * @return 链接集
     */
    PageDTO<LinkDTO> getAll();

    /**
     * 更新友链
     * @param list list
     */
    void updateLinks(List<Object> list);

    /**
     * 删除链接
     * @param link 地址
     */
    void deleteLink(String link);
}
