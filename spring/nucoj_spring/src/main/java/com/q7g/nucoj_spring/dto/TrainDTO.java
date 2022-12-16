package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.Train;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 题单信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("题单信息")
public class TrainDTO {
    /**
     * 题单ID
     */
    @ApiModelProperty(name = "id", value = "题单ID", dataType = "string")
    private String id;
    /**
     * 题单标题
     */
    @ApiModelProperty(name = "title", value = "题单标题", dataType = "string")
    private String title;
    /**
     * 题单创建人
     */
    @ApiModelProperty(name = "author", value = "题单创建人", dataType = "string")
    private String author;
    /**
     * 题单题目列表
     */
    @ApiModelProperty(name = "problems", value = "题单题目集", dataType = "string")
    private List<ProblemDTO> problems;
}
