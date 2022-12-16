package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.Discuss;
import com.q7g.nucoj_spring.po.Solution;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题解信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("题解信息")
public class SolutionDTO {
    /**
     * 题解ID
     */
    @ApiModelProperty(name = "id", value = "题解ID", dataType = "string")
    private String id;
    /**
     * 题解标题
     */
    @ApiModelProperty(name = "title", value = "题解标题", dataType = "string")
    private String title;
    /**
     * 题解创建时间
     */
    @ApiModelProperty(name = "time", value = "题解创建时间", dataType = "long")
    private Long time;
    /**
     * 题解创建人
     */
    @ApiModelProperty(name = "author", value = "题解创建人", dataType = "string")
    private String author;
    /**
     * 题解内容
     */
    @ApiModelProperty(name = "context", value = "题解正文", dataType = "string")
    private String context;

    public static SolutionDTO of(Solution solution, boolean isAll) {
        return SolutionDTO.builder()
                .id(solution.getId())
                .author(solution.getAuthor())
                .title(solution.getTitle())
                .time(solution.getTime())
                .context(isAll ? solution.getContext() : null)
                .build();
    }
}
