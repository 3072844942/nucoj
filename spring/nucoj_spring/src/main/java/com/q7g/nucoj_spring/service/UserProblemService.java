package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.enums.ProblemStatusEnum;
import com.q7g.nucoj_spring.vo.Result;

import java.util.List;

/**
 * 用户完成题目情况
 */
public interface UserProblemService {
    /**
     * 获取用户完成的题目
     * @param userId 用户ID
     * @return 题目ID集
     */
    List<String> getUserAcceptProblem(String userId) ;

    /**
     * 获取用户尝试过的题目
     * @param userId 用户ID
     * @return 题目ID集
     */
    List<String> getUserTryProblem(String userId) ;

    /**
     * 获取题目的通过状态
     * @param problemId 题目ID
     * @return
     */
    ProblemStatusEnum getUserProblemStatus(String userId, String problemId) ;
}
