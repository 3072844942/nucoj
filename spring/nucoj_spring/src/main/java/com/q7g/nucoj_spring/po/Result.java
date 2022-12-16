package com.q7g.nucoj_spring.po;

import com.q7g.nucoj_spring.enums.LanguageEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("judge_result")
public class Result {
    private List<JudgeResult> judgeResults;
//    private LanguageEnum language;
    @Id
    private String submissionId;
    private Long judgeEndTime;
    private List<String> extraInfo;
}
