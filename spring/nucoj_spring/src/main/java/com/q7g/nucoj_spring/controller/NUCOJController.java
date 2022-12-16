package com.q7g.nucoj_spring.controller;

import com.q7g.nucoj_spring.po.NUCOJ;
import com.q7g.nucoj_spring.repository.NUCOJRepository;
import com.q7g.nucoj_spring.vo.ConditionVO;
import com.q7g.nucoj_spring.vo.NUCOJVO;
import com.q7g.nucoj_spring.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NUCOJController {

    @Autowired
    private NUCOJRepository nucojRepository;

    @GetMapping("/")
    public Result<?> getNUCOJInfo() {
        Map<String, Object> map = new HashMap<>();
        String title = nucojRepository.findAll(Sort.by(Sort.Direction.DESC, "time")).get(0).getTitle();
        map.put("title", title);
        return Result.ok(map);
    }

    @PostMapping("/info/update")
    public Result<?> getNUCOJInfo(@RequestBody NUCOJVO nucojVO) {
        NUCOJ nucoj = NUCOJ.builder()
                .title(nucojVO.getTitle())
                .time(System.currentTimeMillis())
                .build();
        nucojRepository.insert(nucoj);
        return Result.ok();
    }
}
