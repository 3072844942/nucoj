package com.q7g.nucoj_spring.po;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemState {
    private String problemId;
    private Long punish;
    private Integer wrong;
    private Boolean accept;
}
