package com.q7g.nucoj_spring.config;

import com.q7g.nucoj_spring.service.LogService;
import com.q7g.nucoj_spring.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 日志记录
 */
@Configuration
public class HttpTraceConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpTraceConfiguration.class);

    @Bean
    public OncePerRequestFilter contentCachingRequestFilter() {
        return new OncePerRequestFilter() {
            @Autowired
            private LogService logService;

            @Override
            protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
                ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
                ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
                filterChain.doFilter(wrappedRequest, wrappedResponse);
                    // 白名单
                    List<String> white = new LinkedList<>(Arrays.asList("/user/login", ""));
                    String token = "";
                    if (!Objects.isNull(request.getHeader("token")))
                            token = request.getHeader("token");

                    // 查询请求参数
                    String query = "";
                    // 如果不为空
                    query = new String(wrappedRequest.getContentAsByteArray());
                    if (query.length() > 100)
                        query = query.substring(0, 100) + "...";
                    // 查询返回数据
                    String result = "";
                    // 如果不为空
                    wrappedResponse.getContentAsByteArray();
                    result = new String(wrappedResponse.getContentAsByteArray());
                    if (result.length() > 100)
                        result = result.substring(0, 100) + "...";

                    // 如果不在白名单
                    if (!white.contains(request.getServletPath()) && !token.equals("")) {
                        try {
                            logService.insertLog(
                                    request.getServletPath(),
                                    request.getMethod(),
                                    query,
                                    result,
                                    JwtUtil.parseJWT(token).getSubject()
                            );
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                wrappedResponse.copyBodyToResponse();
            }
        };
    }
}