package com.q7g.nucoj_spring.vo;

import com.q7g.nucoj_spring.enums.LanguageEnum;
import com.q7g.nucoj_spring.po.ProblemExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 题目信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("题目提交信息")
public class ProblemVO {
    private String contestId;
    /**
     * 语言
     */
    private LanguageEnum language;
    /**
     * 提交的用户ID
     */
    @ApiModelProperty(name = "userId", value = "用户ID", required = true, dataType = "string")
    private String userId;
    /**
     * 代码
     */
    @ApiModelProperty(name = "code", value = "题目代码", required = true, dataType = "string")
    private String code;
    /**
     * 测试样例
     */
    @ApiModelProperty(name = "test", value = "题目测试样例", dataType = "string")
    private String test;
    /**
     * 题目ID
     */
    private String id;
    /**
     * 题目标题
     */
    private String title;
    /**
     * 题目等级
     */
    private String grade;
    /**
     * 题目标签列表
     */
    private List<String> tags;
    /**
     * 题目描述
     */
    private String context;
    /**
     * 题目输入描述
     */
    private String inputContext;
    /**
     * 题目输出描述
     */
    private String outputContext;

    /**
     * 题目样例
     */
    private List<ProblemExample> examples;

    /**
     * 运行时间限制 ms
     */
    private Integer timeRequire;

    /**
     * 内存限制 kb
     */
    private Integer memoryRequire;

    /**
     * 测试数据地址
     */
    private String recordId;

    private Boolean isSpecial;

    private String specialJudge;

    private String specialLanguage;
}
