package com.q7g.nucoj_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 接口状态码枚举
 **/
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(20000, "操作成功"),
    /**
     * 失败
     */
    FAIL(50000, "失败"),
    /**
     * 参数校验失败
     */
    VALID_ERROR(30000, "参数格式不正确"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(40000, "系统异常"),

    /**
     * 邮箱不合法
     */
    NOT_EMAIL(1, "请输入正确的邮箱"),
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(1, "用户不存在"),
    /**
     * 用户名和密码不匹配
     */
    Email_NOT_MATCH_PASSWORD(2, "用户名和密码不匹配");


    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

}
