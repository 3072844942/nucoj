package com.q7g.judgehost;

import com.alibaba.fastjson.JSONObject;
import com.q7g.judgehost.network.HttpRequest;
import com.q7g.judgehost.utils.HttpUtil;
import com.q7g.judgehost.vo.JudgeConditionVO;
import io.micrometer.core.instrument.util.StringEscapeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class JudgeHostApplicationTests {

    @Test
    void contextLoads() {
        String inputFile = "https://www.static.snak.space/7.in";
        String outputFile = "https://www.static.snak.space/7.out";

        Resource inputFileResource = HttpRequest.getFile(inputFile);
        Resource outputFileResource = HttpRequest.getFile(outputFile);

    }
}