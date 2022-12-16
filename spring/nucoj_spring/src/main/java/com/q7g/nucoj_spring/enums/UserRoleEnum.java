package com.q7g.nucoj_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户角色枚举
 */
@Getter
@AllArgsConstructor
public enum UserRoleEnum {
    /**
     * ROOT用户
     */
    ROOT(0),
    /**
     * 管理员
     */
    MANAGER(1),
    /**
     * 成员
     */
    MEMBER(2),
    /**
     * 普通用户
     */
    USER(3);
    private final int code;
}
