package com.q7g.judgehost.core.handler;

import com.q7g.judgehost.core.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求拦截器
 *
 * @author yuzhanglong
 * @date 2020-7-2 8:47
 */

@Configuration
public class HttpRequestHandler implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public RequestInterceptor authenticationInterceptor() {
        return new RequestInterceptor();
    }
}