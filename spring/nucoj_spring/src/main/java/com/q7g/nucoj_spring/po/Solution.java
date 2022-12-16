package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 题解
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("solution")
public class Solution {
    /**
     * 题解ID
     */
    private String id;
    /**
     * 题解标题
     */
    private String title;
    /**
     * 题解创建时间
     */
    private Long time;
    /**
     * 题解创建人
     */
    private String author;
    /**
     * 题解正文
     */
    private String context;
}
