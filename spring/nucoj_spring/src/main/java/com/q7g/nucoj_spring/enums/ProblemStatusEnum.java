package com.q7g.nucoj_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 题目通过状态
 */
@Getter
@AllArgsConstructor
public enum ProblemStatusEnum {
    /**
     * 通过
     */
    ACCEPT(1),
    /**
     * 未通过
     */
    WRONG(2),
    /**
     * 未提交
     */
    NONE(0);
    /**
     * 状态码
     */
    private final Integer Code;
}
