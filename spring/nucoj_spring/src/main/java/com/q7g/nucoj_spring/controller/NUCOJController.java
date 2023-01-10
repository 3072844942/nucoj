package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.po.NUCOJ;
import com.q7g.nucoj_spring.repository.NUCOJRepository;
import com.q7g.nucoj_spring.vo.NUCOJVO;
import com.q7g.nucoj_spring.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NUCOJController {

    @Autowired
    private NUCOJRepository nucojRepository;

    @GetMapping("/")
    public Result<?> getNUCOJInfo() {
        NUCOJ nucoj = nucojRepository.findFirstByOrderByTimeDesc();
        return Result.ok(nucoj);
    }

    @PostMapping("/info/update")
    public Result<?> getNUCOJInfo(@RequestBody NUCOJVO nucojVO) {
        NUCOJ nucoj = NUCOJ.builder()
                .title(nucojVO.getTitle())
                .websocketUrl(nucojVO.getWebsocketUrl())
                .time(System.currentTimeMillis())
                .build();
        nucojRepository.insert(nucoj);
        return Result.ok();
    }
}
