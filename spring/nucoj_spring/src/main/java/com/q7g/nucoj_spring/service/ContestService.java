package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.*;
import com.q7g.nucoj_spring.po.Contest;
import com.q7g.nucoj_spring.po.Problem;

import java.util.List;

public interface ContestService {
    /**
     * 获取最近更新的比赛
     *
     * @return 比赛信息
     */
    List<ContestDTO> getRecentContest(int size) ;

    /**
     * 获取符合条件的比赛
     * @param current 页码
     * @param size 条数
     * @param keywords 搜索条件
     * @return 比赛集
     */
    PageDTO<ContestDTO> getContests(int current, int size, String keywords);

    /**
     * 获取比赛详情
     * @param contestId 比赛ID
     * @return 比赛信息
     */
    ContestDTO getContest(String contestId, boolean admin, String userId);

    /**
     * 删除比赛
     * @param contestId 比赛Id
     */
    void deleteContest(String contestId);

    /**
     * 获取比赛题目详情
     * @param contestId 比赛Id
     * @param problemId 题目Id
     * @return
     */
    ProblemDTO getAdminContestProblem(String contestId, String problemId);

    /**
     * 更新题目比赛
     * @param contestId 比赛Id
     * @param problem 题目对象
     */
    void updateContestProblem(String contestId, Problem problem);

    /**
     * 更新比赛
     * @param contest 比赛对象
     */
    void updateContest(Contest contest);

    /**
     * 删除比赛题目
     * @param contestId 比赛Id
     * @param problemId 题目Id
     */
    void deleteContestProblem(String contestId, String problemId);

    /**
     * 获取参加比赛的用户
     * @param contestId 比赛Id
     * @param current 页码
     * @param size 条数
     * @return
     */
    PageDTO<UserDetailDTO> getContestUser(String contestId, int current, int size);

    ProblemDTO getContestProblem(String contestId, String problemId);

    JudgeStatusDTO judge(String contestId, String language, String problemId, String userId, String code);

    ContestRankDTO getContestRank(String contestId, int current, int size, String userId);

    ContestRankUserDTO getUserContestState(String userId, String contestId);

    PageDTO<ContestSubmitDTO> getContestSubmits(String contestId, int current, int size);
}
