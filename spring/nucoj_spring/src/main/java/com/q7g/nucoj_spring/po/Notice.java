package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 公告
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("notice")
public class Notice {
    /**
     * 公告ID
     */
    private String id;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告创建时间
     */
    private Long time;
    /**
     * 公告创建人
     */
    private String author;
    /**
     * 公告正文
     */
    private String context;
}
