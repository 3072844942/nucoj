package com.q7g.nucoj_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色权限枚举
 */
@Getter
@AllArgsConstructor
public enum PermissionEnum {
    /**
     * 比赛
     */
//    CONTEST_DETAIL("contest_detail"),
    /**
     * 题目Debug
     */
    PROBLEM_DEBUG("problem_debug"),
    /**
     * 题目提交
     */
    PROBLEM_SUBMIT("problem_submit")
    ;
    private final String message;
}
