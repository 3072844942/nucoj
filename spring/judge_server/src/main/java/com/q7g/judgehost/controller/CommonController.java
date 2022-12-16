package com.q7g.judgehost.controller;

import com.github.dozermapper.core.Mapper;
import com.q7g.judgehost.bo.JudgeHostConditionBO;
import com.q7g.judgehost.bo.JudgeHostConfigurationBO;
import com.q7g.judgehost.core.common.UnifiedResponse;
import com.q7g.judgehost.dto.SetWorkingAmountDTO;
import com.q7g.judgehost.service.CommonService;
import com.q7g.judgehost.vo.JudgeHostConditionVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 一般功能性接口
 */

@RestController
@RequestMapping("/common")
public class CommonController {
    private final CommonService commonService;
    private final Mapper mapper;

    public CommonController(CommonService commonService, Mapper mapper) {
        this.commonService = commonService;
        this.mapper = mapper;
    }

    /**
     * 测试连接，并返回判题机相关信息和状态
     *
     * @author yuzhanglong
     * @date 2020-8-17 19:40:42
     */
    @GetMapping("/connection_test")
    private UnifiedResponse testConnection() {
        JudgeHostConfigurationBO judgeHostInfo = commonService.getJudgeHostConfiguration();
        JudgeHostConditionBO conditionBO = commonService.getJudgeHostCondition();
        JudgeHostConditionVO conditionVO = mapper.map(conditionBO, JudgeHostConditionVO.class);
        conditionVO.setPort(judgeHostInfo.getPort());
        conditionVO.setVersion(judgeHostInfo.getVersion());
        conditionVO.setScriptPath(judgeHostInfo.getScriptPath());
        conditionVO.setResolutionPath(judgeHostInfo.getResolutionPath());
        conditionVO.setWorkPath(judgeHostInfo.getWorkPath());
        return new UnifiedResponse("judgeHost运行正常", conditionVO);
    }


    /**
     * 设置判题节点数量
     *
     * @author yuzhanglong
     * @date 2020-8-29 17:14:14
     * @deprecated 将被废弃
     */
    @PutMapping("/max_working_amount")
    private UnifiedResponse setMaxWorkingAmount(@Validated @RequestBody SetWorkingAmountDTO workingAmountDTO) {
        commonService.setJudgeHostWorkingAmount(
                workingAmountDTO.getMaxWorkingAmount(),
                workingAmountDTO.getForceSet()
        );
        return new UnifiedResponse("设置判题节点数量成功");
    }
}
