package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Submit {
    private String id;
    private String problemId;
    private String userId;
    private Integer condition;
    private String language;
    private String code;
    private Long time;
}
