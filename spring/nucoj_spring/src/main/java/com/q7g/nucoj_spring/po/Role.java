package com.q7g.nucoj_spring.po;

import com.q7g.nucoj_spring.enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 角色权限表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("role")
public class Role {
    /**
     * 角色信息
     */
    @Id
    private UserRoleEnum role;

    /**
     * 角色权限
     */
    private List<String> permissions;
    /**
     * 角色菜单
     */
    private List<Menu> menus;
}
