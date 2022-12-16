package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 题单
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("train")
public class Train {
    /**
     * 题单ID
     */
    @Id
    private String id;
    /**
     * 题单标题
     */
    private String title;
    /**
     * 题单创建人
     */
    private String author;
    /**
     * 题单题目ID集
     */
    private List<String> problems;
}
