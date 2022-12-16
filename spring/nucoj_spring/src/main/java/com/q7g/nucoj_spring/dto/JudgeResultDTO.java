package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.JudgeResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.retry.annotation.Backoff;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("单个评测结果")
public class JudgeResultDTO {
    /**
     * 运行时间
     */
    @ApiModelProperty(name = "realTimeCost", value = "运行时间",  dataType = "string")
    private String realTimeCost;
    /**
     * 内存花费
     */
    @ApiModelProperty(name = "memoryCost", value = "内存花费",  dataType = "string")
    private String memoryCost;
    /**
     * cpu运行时间
     */
    @ApiModelProperty(name = "cpuTimeCost", value = "cpu运行时间",  dataType = "string")
    private String cpuTimeCost;
    /**
     * 评测状态
     */
    @ApiModelProperty(name = "condition", value = "评测状态",  dataType = "integer")
    private Integer condition;
    /**
     * 输入路径
     */
    @ApiModelProperty(name = "stdInPath", value = "输入路径",  dataType = "string")
    private String stdInPath;
    /**
     * 输出路径
     */
    @ApiModelProperty(name = "stdOutPath", value = "输出路径",  dataType = "string")
    private String stdOutPath;
    /**
     * 编辑错误路径
     */
    @ApiModelProperty(name = "stdErrPath", value = "错误路径",  dataType = "string")
    private String stdErrPath;
    /**
     * 评测状态信息
     */
    @ApiModelProperty(name = "message", value = "评测状态信息",  dataType = "string")
    private String message;

    public static JudgeResultDTO of(JudgeResult judgeResult) {
        return JudgeResultDTO.builder()
                .realTimeCost(judgeResult.getRealTimeCost())
                .memoryCost(judgeResult.getMemoryCost())
                .cpuTimeCost(judgeResult.getCpuTimeCost())
                .condition(judgeResult.getCondition())
                .stdInPath(judgeResult.getStdInPath())
                .stdOutPath(judgeResult.getStdOutPath())
                .stdErrPath(judgeResult.getStdErrPath())
                .message(judgeResult.getMessage())
                .build();
    }
}
