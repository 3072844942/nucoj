package com.q7g.nucoj_spring.po;

import com.q7g.nucoj_spring.enums.JudgeModel;
import com.q7g.nucoj_spring.enums.LanguageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Judge {
    private LanguageEnum language;
    private List<ProblemExample> solutions;
    private String submissionCode;
    private JudgeModel judgePreference;
    private Integer memoryLimit;
    private Integer outputLimit;
}
