package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.UserBaseDTO;
import com.q7g.nucoj_spring.enums.ResultCodeEnum;
import com.q7g.nucoj_spring.po.User;
import com.q7g.nucoj_spring.vo.UserVO;

import java.util.List;

/**
 * 用户身份服务
 */
public interface UserAuthService {
    /**
     * 用户登录
     * @return 用户常规信息
     */
    UserBaseDTO login(String username, String password);
    void logout();
    /**
     * 发送验证码
     * @param email 邮箱地址
     */
    void sendCode(String email);

    /**
     * 用户注册
     */
    void register(String username, String password, String code);

    /**
     * 用户修改
     */
    void modify(String username, String password, String code);

    /**
     * 更新用户角色
     * @param userId 用户Id
     * @param role 更新后角色
     */
    void updateUserRole(String userId, int role);

    /**
     * 删除用户
     * @param id 用户Id
     */
    void deleteUser(String id);

    void updateUser(String userId, String nickName, String name, String avatar, String number, String college, String qq, String blog, String github);
}
