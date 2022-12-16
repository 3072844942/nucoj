package com.q7g.nucoj_spring.util;

import com.q7g.nucoj_spring.exceotion.BizException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static final int MAX_IN_MEMORY_SIZE = 500 * 1024 * 1024;

    public static HttpEntity<Map<String, Object>> mapMapToPostJson(Map<String, Object> jsonMap) {

        //如果需要其它的请求头信息、都可以在这里追加
        HttpHeaders httpHeaders = new HttpHeaders();

        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");

        httpHeaders.setContentType(type);

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(jsonMap, httpHeaders);

        return httpEntity;
    }

    public static HttpEntity<List<Object>> mapListToPostJson(List<Object> json) {

        //如果需要其它的请求头信息、都可以在这里追加
        HttpHeaders httpHeaders = new HttpHeaders();

        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");

        httpHeaders.setContentType(type);

        HttpEntity<List<Object>> httpEntity = new HttpEntity<>(json, httpHeaders);

        return httpEntity;
    }

    public static Resource getFile(String url) {
        Mono<ClientResponse> responseMono = createWebClient()
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .exchange();
        ClientResponse response = responseMono.block();
        if (response == null) {
            throw new BizException("B1004");
        }
        return response.bodyToMono(Resource.class).block();
    }

    private static WebClient createWebClient() {
        return WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(HttpUtil.MAX_IN_MEMORY_SIZE))
                        .build())
                .build();
    }
}
