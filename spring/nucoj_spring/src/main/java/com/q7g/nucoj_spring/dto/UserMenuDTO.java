package com.q7g.nucoj_spring.dto;

import com.q7g.nucoj_spring.po.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户菜单详情
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuDTO {
    /**
     * 菜单标题
     */
    @ApiModelProperty(name = "label", value = "菜单标题", dataType = "string")
    private String label;
    /**
     * 菜单图标
     * 由前端来判断用哪个图标
     */
    @ApiModelProperty(name = "icon", value = "菜单图标", dataType = "string")
    private String icon;
    /**
     * 菜单对应地址
     */
    @ApiModelProperty(name = "key", value = "key", dataType = "string")
    private String key;
    /**
     * 子菜单
     */
    @ApiModelProperty(name = "children", value = "子菜单", dataType = "list")
    private List<UserMenuDTO> children;
    /**
     * 类型
     * group | null
     */
    @ApiModelProperty(name = "type", value = "菜单类型", dataType = "string")
    private String type;

    public UserMenuDTO(Menu menu) {
        this.label = menu.getLabel();
        this.icon = menu.getIcon();
        this.key = menu.getKey();
        this.type = menu.getType();
        this.children = menu.getChildren().stream().map(UserMenuDTO::new).collect(Collectors.toList());
    }
}
