package com.q7g.nucoj_spring.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色菜单
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    /**
     * 菜单标题
     */
    private String label;
    /**
     * 菜单图标
     * 由前端来判断用哪个图标
     */
    private String icon;
    /**
     * 菜单对应地址
     */
    private String key;
    /**
     * 子菜单
     */
    private List<Menu> children;
    /**
     * 类型
     * group | null
     */
    private String type;
}
