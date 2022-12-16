package com.q7g.nucoj_spring.service;


import java.util.List;

/**
 * 标签服务
 */
public interface TagService {
    /**
     * 获取所有标签
     * @return 标签集
     */
    List<String> getTags();

    /**
     * 获取所有等级
     * @return 等级集
     */
    List<String> getGrades();
}
