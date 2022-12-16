package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.SolutionDTO;

/**
 * 题解服务
 */
public interface SolutionService {
    /**
     * 获取满足条件的题解
     * @param current 页码
     * @param size 条数
     * @param keywords 搜索内容
     * @return 题解集
     */
    PageDTO<SolutionDTO> getSolutions(int current, int size, String keywords, String userId);

    /**
     * 获取题解详情
     * @param solutionId 题解ID
     * @return 题解信息
     */
    SolutionDTO getSolution(String solutionId);

    /**
     * 更新题解
     * @param id id
     * @param title title
     * @param time time
     * @param context context
     * @param author author
     */
    void updateSolution(String id, String title, Long time, String context, String author);

    /**
     * 删除题解
     * @param solutionId id
     */
    void deleteSolution(String solutionId);
}
