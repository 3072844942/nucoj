package com.q7g.nucoj_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestRankUserProblemDTO {
    private Long punish;
    private Integer wrong;
    private Boolean accept;
}
