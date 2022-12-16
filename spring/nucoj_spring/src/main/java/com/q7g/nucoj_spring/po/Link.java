package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 友情链接
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("link")
public class Link {
    /**
     * 标题
     */
    private String title;
    /**
     * 链接地址
     */
    @Id
    private String link;
}
