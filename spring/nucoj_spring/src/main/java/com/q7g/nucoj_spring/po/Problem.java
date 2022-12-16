package com.q7g.nucoj_spring.po;

import com.q7g.nucoj_spring.vo.ProblemVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 题目
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("problem")
public class Problem {
    /**
     * 题目ID
     */
    @Id
    private String id;
    /**
     * 题目标题
     */
    private String title;
    /**
     * 题目创建时间
     */
    private Long time;
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

    private String rate;

    public static Problem of(ProblemVO problemVO) {
        return Problem.builder()
                .id(problemVO.getId())
                .title(problemVO.getTitle())
                .time(System.currentTimeMillis())
                .grade(problemVO.getGrade())
                .tags(problemVO.getTags())
                .context(problemVO.getContext())
                .inputContext(problemVO.getInputContext())
                .outputContext(problemVO.getOutputContext())
                .examples(problemVO.getExamples())
                .timeRequire(problemVO.getTimeRequire())
                .memoryRequire(problemVO.getMemoryRequire())
                .recordId(problemVO.getRecordId())
                .isSpecial(problemVO.getIsSpecial())
                .specialJudge(problemVO.getSpecialJudge())
                .specialLanguage(problemVO.getSpecialLanguage())
                .rate("0/0")
                .build();
    }
}
