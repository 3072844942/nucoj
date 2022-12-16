package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.Contest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 比赛信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("比赛信息")
public class ContestDTO {
    /**
     * 比赛ID
     */
    @ApiModelProperty(name = "id", value = "比赛ID",  dataType = "string")
    private String id;
    /**
     * 比赛标题
     */
    @ApiModelProperty(name = "title", value = "比赛标题", dataType = "string")
    private String title;
    /**
     * 比赛开始时间
     */
    @ApiModelProperty(name = "email", value = "比赛开始时间", dataType = "long")
    private Long startTime;
    /**
     * 比赛持续时长
     */
    @ApiModelProperty(name = "tiem", value = "比赛时长", dataType = "long")
    private Long time;
    /**
     * 比赛创建人
     */
    @ApiModelProperty(name = "author", value = "比赛创建人", dataType = "string")
    private String author;
    private Integer number;
    private Boolean entry;
    /**
     * 比赛题目集
     */
    @ApiModelProperty(name = "problems", value = "比赛题目列表", dataType = "array")
    private List<ProblemDTO> problems;

    private String context;

    /**
     *
     * 0 - 未开始 前台
     * 1 - 开始 所有题目 前台
     * 2 - all 后台
     * @param contest
     * @param type
     * @param userId
     * @return
     */
    public static ContestDTO of(Contest contest, int type, String userId) {
        return ContestDTO.builder()
                .id(contest.getId())
                .startTime(contest.getStartTime())
                .time(contest.getTime())
                .title(contest.getTitle())
                .author(contest.getAuthor())
                .number(contest.getEntryUsers().size())
                .entry(!userId.equals("") && contest.getEntryUsers().contains(userId))
                .problems(type > 0 ? contest.getProblems().stream().map(item -> ProblemDTO.of(item, type)).collect(Collectors.toList()): null)
                .context(contest.getContext())
                .build();
    }
}
