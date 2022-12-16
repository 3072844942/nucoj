package com.q7g.nucoj_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestSubmitDTO {
    private String id;
    private String problemId;
    private String user;
    private Integer condition;
    private String language;
    private String code;
    private Long time;
}
