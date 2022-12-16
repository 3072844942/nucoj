package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * 题目测试样例
 * 这里应该全是地址
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemExample {
    /**
     * 输入样例
     */
    private String test;
    /**
     * 输出样例
     */
    private String answer;
}

