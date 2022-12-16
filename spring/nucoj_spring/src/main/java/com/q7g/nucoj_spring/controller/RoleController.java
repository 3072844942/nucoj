package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.RoleDTO;
import com.q7g.nucoj_spring.service.RoleService;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色信息模块
 */
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 获取角色集
     * @param conditionVO
     * @return
     */
    @PostMapping("/role/list")
    @ApiOperation(value = "获取角色集")
    public Result<PageDTO<RoleDTO>> getRoles(@RequestBody ConditionVO conditionVO) {
        return Result.ok(roleService.getRoles(conditionVO.getCurrent(), conditionVO.getSize()));
    }
}
