package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.util.HttpUtil;
import com.q7g.nucoj_spring.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OjController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取判题后端状态
     * @return
     */
    @GetMapping("/common/connection_test")
    @ApiOperation(value = "获取判题后端状态")
    public Result<?> getHost() {
        // 直接转发请求
        Object forObject = restTemplate.getForObject("http://JUDGE/common/connection_test", Object.class);
        return Result.ok(forObject);
    }
}
