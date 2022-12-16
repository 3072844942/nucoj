package com.q7g.nucoj_spring.service;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.RoleDTO;

/**
 * 角色服务
 */
public interface RoleService {
    /**
     * 获取角色集
     * @param current 页码
     * @param size 条数
     * @return
     */
    PageDTO<RoleDTO> getRoles(int current, int size);
}
