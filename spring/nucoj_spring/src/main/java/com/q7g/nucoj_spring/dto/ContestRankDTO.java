package com.q7g.nucoj_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContestRankDTO {
    private List<ContestColumnDTO> columns;
    private List<ContestRankUserDTO> data;
    private Long total;
}
