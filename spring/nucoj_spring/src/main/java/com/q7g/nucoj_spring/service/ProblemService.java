package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.JudgeStatusDTO;
import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.ProblemDTO;
import com.q7g.nucoj_spring.po.Problem;

import java.util.List;

public interface ProblemService {
    /**
     * 获取最近题目
     * @param size 条数
     * @return 题目集
     */
    public List<ProblemDTO> getRecentProblem(int size);

    /**
     * 获取题目详情
     * @param problemId 题目ID
     * @return 题目信息
     */
    ProblemDTO getProblem(String problemId, boolean admin);

    /**
     * 获取符合条件的题目
     * @param current 页码
     * @param size 条数
     * @param keywords 搜索内容
     * @param tags 标签集
     * @return 题目集
     */
    PageDTO<ProblemDTO> getProblems(int current, int size, String keywords, List<String> tags, boolean admin);

    /**
     * 删除题目
     * @param problemId 题目Id
     */
    void deleteProblem(String problemId);

    /**
     * 更新题目
     * @param problem 题目对象
     */
    void updateProblem(Problem problem);

    JudgeStatusDTO judge(String language, String problemId, String userId, String code);
}
