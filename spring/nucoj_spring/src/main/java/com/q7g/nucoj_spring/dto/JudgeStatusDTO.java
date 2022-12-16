package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.enums.LanguageEnum;
import com.q7g.nucoj_spring.enums.ProblemStatusEnum;
import com.q7g.nucoj_spring.po.JudgeResult;
import com.q7g.nucoj_spring.po.Result;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 评测结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("评测结果")
public class JudgeStatusDTO {
    /**
     * 评测结果集
     */
    @ApiModelProperty(name = "judgeResults", value = "评测结果集",  dataType = "array")
    private List<JudgeResultDTO> judgeResults;
    /**
     * 评测Id
     */
    @ApiModelProperty(name = "submissionId", value = "评测ID",  dataType = "string")
    private String submissionId;
    /**
     * 评测结束时间
     */
    @ApiModelProperty(name = "judgeEndTime", value = "评测结束时间",  dataType = "long")
    private Long judgeEndTime;
    /**
     * 额外信息
     */
    @ApiModelProperty(name = "extraInfo", value = "额外信息",  dataType = "array")
    private List<String> extraInfo;

    public static JudgeStatusDTO of (Result result) {
        return JudgeStatusDTO.builder()
                .judgeResults(result.getJudgeResults().stream().map(JudgeResultDTO::of).collect(Collectors.toList()))
                .submissionId(result.getSubmissionId())
                .judgeEndTime(result.getJudgeEndTime())
                .extraInfo(result.getExtraInfo())
                .build();
    }
}
