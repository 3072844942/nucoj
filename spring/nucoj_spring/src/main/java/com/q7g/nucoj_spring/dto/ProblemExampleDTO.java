package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.ProblemExample;
import com.q7g.nucoj_spring.util.HttpUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
/**
 * 题目样例信息
 */
@Getter
@Builder
@AllArgsConstructor
@ApiModel("题目样例信息")
public class ProblemExampleDTO {
    /**
     * 输入样例
     */
    @ApiModelProperty(name = "test", value = "输入样例", dataType = "string")
    private String test;
    /**
     * 输出样例
     */
    @ApiModelProperty(name = "answer", value = "输出样例", dataType = "string")
    private String answer;

    public static ProblemExampleDTO of(ProblemExample problemExample) {
        return ProblemExampleDTO.builder()
                .test(problemExample.getTest())
                .answer(problemExample.getAnswer())
                .build();
    }
}
