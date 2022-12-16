package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.TrainDTO;
import com.q7g.nucoj_spring.service.TrainService;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题单模块
 */
@RestController
@Api(tags = "题单模块")
public class TrainController {
    @Autowired
    private TrainService trainService;

    /**
     * 获取符合条件的题单
     *
     * @param condition 条件
     * @return {@link Result<List<TrainDTO>> } 题单集合
     */
    @PostMapping("/train/list")
    @ApiOperation(value = "获取题单集")
    public Result<PageDTO<TrainDTO>> getTrains(@RequestBody ConditionVO condition) {
        return Result.ok(trainService.getTrains(condition.getCurrent(), condition.getSize(), condition.getKeywords()));
    }

    /**
     * 获取题单详情
     * @param trainId 题单ID
     * @return {@link Result<TrainDTO>} 题单信息
     */
    @GetMapping("/train/{trainId}")
    @ApiOperation(value = "获取题单详情")
    public Result<TrainDTO> getTrain(@PathVariable String trainId) {
        return Result.ok(trainService.getTrain(trainId));
    }
}
