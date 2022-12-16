package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.UserBaseDTO;
import com.q7g.nucoj_spring.dto.UserDetailDTO;
import com.q7g.nucoj_spring.dto.UserMenuDTO;
import com.q7g.nucoj_spring.po.User;

import java.util.List;

/**
 * 用户信息
 */
public interface UserInfoService {
    /**
     * 获取用户的菜单
     * @param userId 用户邮箱 | 用户ID
     * @return
     */
    List<UserMenuDTO> getUserMenu(String userId);

    /**
     * 获取用户集
     * @param current 页码
     * @param size 条数
     * @return
     */
    PageDTO<UserDetailDTO> getUsers(int current, int size);

    /**
     * 获取用户排名
     * @param size 条数
     * @return 用户集
     */
    List<UserBaseDTO> getUserRank(int size);

    /**
     * 获取用户参加的比赛Id
     * @param userId 用户Id
     * @return
     */
    List<String> getUserContest(String userId);

    /**
     * 加入比赛
     * @param userId
     * @param contestId
     */
    void entryContest(String userId, String contestId);

    /**
     * 获取用户信息
     * @param userId 用户Id
     * @return
     */
    User getUser(String userId);

    void updateUserAvatar(String userId, String url);
}
