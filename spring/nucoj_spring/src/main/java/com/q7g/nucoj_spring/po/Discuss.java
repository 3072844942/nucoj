package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 分享
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("discuss")
public class Discuss {
    /**
     * 分享ID
     */
    @Id
    private String id;
    /**
     * 分享标题
     */
    private String title;
    /**
     * 分享创建时间
     */
    private Long time;
    /**
     * 分享创建人
     */
    private String author;
    /**
     * 分享正文
     */
    private String context;
}
