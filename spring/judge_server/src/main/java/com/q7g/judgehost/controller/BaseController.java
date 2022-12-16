package com.q7g.judgehost.controller;

import com.q7g.judgehost.core.common.UnifiedResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 增加 baseController
 */

@RestController
public class BaseController {

    /**
     * 欢迎 -- 表示部署成功
     *
     * @author yuzhanglong
     * @date 2020-9-13 17:56:20
     */

    @GetMapping("/")
    public UnifiedResponse getWelcomeText() {
        return new UnifiedResponse("Your project is running successfully! O(∩_∩)O");
    }
}
