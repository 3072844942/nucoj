package com.q7g.nucoj_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JudgeModel {
    OI("OI"),
    ACM("ACM")
    ;
    private final String message;
}
