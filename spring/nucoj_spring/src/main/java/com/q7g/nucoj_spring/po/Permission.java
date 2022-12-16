package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 总权限表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("permission")
public class Permission {
    @Id
    private String title;

    private List<String> permissions;
}
