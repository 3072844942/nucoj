package com.q7g.nucoj_spring.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageEnum {
    JAVA("JAVA"),
    PYTHON("PYTHON"),
    C("C"),
    C_PLUS_PLUS("C_PLUS_PLUS")
    ;
    private String language;
}
