package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.LogDTO;
import com.q7g.nucoj_spring.service.LogService;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 日志模块
 */
@RestController
@Api("日志模块")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 查看操作日志
     *
     * @param conditionVO 条件
     * @return  日志列表
     */
    @ApiOperation(value = "查看操作日志")
    @PostMapping("/log/list")
    public Result<PageDTO<LogDTO>> getLogs(@RequestBody ConditionVO conditionVO) {
        return Result.ok(logService.getLogs(conditionVO.getCurrent(), conditionVO.getSize()));
    }

    /**
     * 删除日志
     * @param conditionVO 日志Id
     * @return
     */
    @ApiOperation(value = "删除操作日志")
    @PostMapping("/log/delete")
    public Result<?> deleteLog(@RequestBody ConditionVO conditionVO) {
        logService.deleteLog(conditionVO.getId());
        return Result.ok();
    }
}
