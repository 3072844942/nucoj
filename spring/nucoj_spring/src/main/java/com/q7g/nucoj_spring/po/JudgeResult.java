package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JudgeResult {
    private String realTimeCost;
    private String memoryCost;
    private String cpuTimeCost;
    private Integer condition;
    private String stdInPath;
    private String stdOutPath;
    private String stdErrPath;
    private String message;
    private String answer;
}
