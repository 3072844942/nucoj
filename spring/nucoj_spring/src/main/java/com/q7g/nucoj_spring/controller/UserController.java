package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.UserBaseDTO;
import com.q7g.nucoj_spring.dto.UserDetailDTO;
import com.q7g.nucoj_spring.dto.UserMenuDTO;
import com.q7g.nucoj_spring.po.User;
import com.q7g.nucoj_spring.service.UserAuthService;
import com.q7g.nucoj_spring.service.UserInfoService;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.Result;
import com.q7g.nucoj_spring.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户模块
 */
@RestController
@Api(tags = "用户模块")
public class UserController {
    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户登录
     * @param user 用户信息
     * @return {@link Result<UserBaseDTO>} 用户基本信息
     */
    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录")
    public Result<UserBaseDTO> login(@RequestBody UserVO user) {
        return Result.ok(userAuthService.login(user.getEmail(), user.getPassword()));
    }

    /**
     * 用户登出
     * @return
     */
    @PostMapping("/user/logout")
    @ApiOperation(value = "用户登出")
    public Result<?> logout() {
        userAuthService.logout();
        return Result.ok();
    }

    /**
     * 发送验证码
     * @param user 用户信息
     * @return {@link Result<>}
     */
    @PostMapping("/user/code")
    @ApiOperation(value = "发送验证码")
    public Result<?> sendCode(@RequestBody UserVO user) {
        userAuthService.sendCode(user.getEmail());
        return Result.ok();
    }
    /**
     * 用户注册
     * @param user 用户信息
     * @return {@link Result<>}
     */
    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册")
    public Result<?> register(@RequestBody UserVO user) {
        userAuthService.register(user.getEmail(), user.getPassword(), user.getCode());
        return Result.ok();
    }

    /**
     * 用户忘记密码
     * @param user 用户信息
     * @return {@link Result<>}
     */
    @PostMapping("/user/modify")
    @ApiOperation(value = "用户修改密码")
    public Result<?> modify(@RequestBody UserVO user) {
        userAuthService.modify(user.getEmail(), user.getPassword(), user.getCode());
        return Result.ok();
    }

    /**
     * 获取用户排名
     * @param size 条数
     * @return {@link Result<List<UserBaseDTO>>} 用户列表
     */
    @GetMapping("/user/rank")
    @ApiOperation(value = "获取用户排民")
    public Result<List<UserBaseDTO>> getUserRank(@RequestParam Integer size) {
        return Result.ok(userInfoService.getUserRank(size));
    }

    /**
     * 获取用户角色菜单
     */
    @PostMapping("/user/menus")
    @ApiOperation(value = "获取用户角色菜单")
    public Result<List<UserMenuDTO>> getUserMenu(@RequestBody UserVO userVO) {
        return Result.ok(userInfoService.getUserMenu(userVO.getEmail()));
    }

    /**
     * 获取用户集
     */
    @PostMapping("/user/list")
    @ApiOperation(value = "获取用户集")
    public Result<PageDTO<UserDetailDTO>> getUsers(@RequestBody ConditionVO conditionVO) {
        return Result.ok(userInfoService.getUsers(conditionVO.getCurrent(), conditionVO.getSize()));
    }

    /**
     * 更新用户角色
     */
    @PostMapping("/user/role/update")
    @ApiOperation(value = "更新用户角色")
    public Result<?> updateUserRole(@RequestBody ConditionVO conditionVO) {
        userAuthService.updateUserRole(conditionVO.getId(), conditionVO.getRole());
        return Result.ok();
    }

    @PostMapping("/user/avatar")
    public Result<?> updateUserAvatar(@RequestBody ConditionVO conditionVO) {
        userInfoService.updateUserAvatar(conditionVO.getUserId(), conditionVO.getKeywords());
        return Result.ok();
    }

    /**
     * 删除用户
     */
    @PostMapping("/user/delete")
    @ApiOperation(value = "删除用户")
    public Result<?> deleteUser(@RequestBody ConditionVO conditionVO) {
        userAuthService.deleteUser(conditionVO.getId());
        return Result.ok();
    }

    @PostMapping("/user/update")
    public Result<?> updateUser(@RequestBody UserVO userVO) {
        userAuthService.updateUser(userVO.getId(), userVO.getNickName(), userVO.getName(), userVO.getAvatar(), userVO.getNumber(), userVO.getCollege(), userVO.getContact().getQq(), userVO.getContact().getBlog(), userVO.getContact().getGithub());
        return Result.ok();
    }
}
