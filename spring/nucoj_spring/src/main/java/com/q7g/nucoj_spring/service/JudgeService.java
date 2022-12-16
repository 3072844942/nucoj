package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.JudgeStatusDTO;
import com.q7g.nucoj_spring.enums.JudgeModel;
import com.q7g.nucoj_spring.enums.ProblemStatusEnum;
import com.q7g.nucoj_spring.po.Problem;

import java.util.List;
import java.util.Map;

public interface JudgeService {
    /**
     * 调试diamond
     *
     * @param code 代码
     * @param test 测试样例
     * @return 测试结果
     */
    String debugCode(String code, String test, String language);

    /**
     * 测试题目
     */
    JudgeStatusDTO judge(Problem problem, String language, String code, JudgeModel judgeModel);
}