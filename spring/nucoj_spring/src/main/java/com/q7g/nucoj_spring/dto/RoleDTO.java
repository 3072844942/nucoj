package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.enums.UserRoleEnum;
import com.q7g.nucoj_spring.po.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * 角色信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    /**
     * 角色信息
     */
    @ApiModelProperty(name = "role", value = "角色名称", dataType = "role")
    private UserRoleEnum role;

    /**
     * 角色权限
     */
    @ApiModelProperty(name = "permissions", value = "权限集合", dataType = "list")
    private List<String> permissions;
    /**
     * 角色菜单
     */
    @ApiModelProperty(name = "menus", value = "角色菜单", dataType = "list")
    private List<UserMenuDTO> menus;
}
