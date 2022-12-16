package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.Problem;
import com.q7g.nucoj_spring.po.ProblemExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("题目信息")
public class ProblemDTO {
    /**
     * 题目ID
     */
    @ApiModelProperty(name = "id", value = "题目ID", dataType = "string")
    private String id;
    /**
     * 题目标题
     */
    @ApiModelProperty(name = "title", value = "题目标题", dataType = "string")
    private String title;
    /**
     * 题目创建时间
     */
    @ApiModelProperty(name = "time", value = "题目创建时间", dataType = "long")
    private Long time;
    /**
     * 题目等级
     */
    @ApiModelProperty(name = "grade", value = "题目等级", dataType = "string")
    private String grade;
    /**
     * 题目标签列表
     */
    @ApiModelProperty(name = "tags", value = "题目标签集", dataType = "array")
    private List<String> tags;
    /**
     * 题目描述
     */
    @ApiModelProperty(name = "context", value = "题目描述", dataType = "string")
    private String context;
    /**
     * 题目输入描述
     */
    @ApiModelProperty(name = "inputContext", value = "题目输入描述", dataType = "string")
    private String inputContext;
    /**
     * 题目输出描述
     */
    @ApiModelProperty(name = "outputContext", value = "题目输出描述", dataType = "string")
    private String outputContext;
    /**
     * 运行时间限制 ms
     */
    @ApiModelProperty(name = "timeRequire", value = "题目时间限制", dataType = "string")
    private Integer timeRequire;

    /**
     * 内存限制 kb
     */
    @ApiModelProperty(name = "memoryRequire", value = "题目内存限制", dataType = "string")
    private Integer memoryRequire;
    /**
     * 题目样例集
     */
    @ApiModelProperty(name = "examples", value = "题目样例", dataType = "string")
    private List<ProblemExampleDTO> examples;
    /**
     * 测试数据地址
     */
    @ApiModelProperty(name = "recordId", value = "测试数据地址", dataType = "string")
    private String recordId;
    /**
     * 是否特殊判断
     */
    @ApiModelProperty(name = "isSpecial", value = "是否特殊判断", dataType = "boolean")
    private Boolean isSpecial;
    /**
     * 特殊判断代码
     */
    @ApiModelProperty(name = "specialJudge", value = "特殊判断代码",  dataType = "string")
    private String specialJudge;
    /**
     * 特殊判断语言
     */
    @ApiModelProperty(name = "specialLanguage", value = "特殊判断语言",  dataType = "string")
    private String specialLanguage;

    private String rate;

    private Boolean state;

    /**
     *
     * @param problem
     * @param isAll
     * 0 - 简略信息。 题目列表集
     * 1 - 前台信息。 不包括评测信息
     * 2 - 后台信息。 详情信息
     * @return
     */
    public static ProblemDTO of(Problem problem, int isAll) {
        return ProblemDTO.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .time(problem.getTime())
                .grade(problem.getGrade())
                .tags(problem.getTags())
                .rate(problem.getRate())
                .context(isAll > 0 ? problem.getContext() : problem.getContext().substring(0, Math.min(100, problem.getContext().length())))
                .inputContext(isAll > 0 ? problem.getInputContext() : null)
                .outputContext(isAll > 0 ? problem.getOutputContext() : null)
                .timeRequire(isAll > 0 ? problem.getTimeRequire() : null)
                .memoryRequire(isAll > 0 ? problem.getMemoryRequire() : null)
                .examples(isAll > 0 ? problem.getExamples().stream().map(ProblemExampleDTO::of).collect(Collectors.toList()) : null)
                .recordId(isAll > 1 ? problem.getRecordId() : null)
                .isSpecial(isAll > 1 ? problem.getIsSpecial() : null)
                .specialJudge(isAll > 1 ? problem.getSpecialJudge() : null)
                .specialLanguage(isAll > 1 ? problem.getSpecialLanguage() : null)
                .build();
    }
}
