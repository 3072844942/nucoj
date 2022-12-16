package com.q7g.nucoj_spring.handler;

import com.alibaba.fastjson.JSONObject;
import com.q7g.nucoj_spring.po.LoginUser;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.service.RedisService;
import com.q7g.nucoj_spring.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.q7g.nucoj_spring.constant.RedisPrefixConst.USER_TOKEN_KEY;

/**
 * jwt认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)) { // 如果没有token
            // 放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        // 解析token
        String username;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            username = claims.getSubject();
        } catch (Exception e) {
            throw new BizException("token非法");
        }
        // 从redis中获取用户信息
        // LoginUser loginUser = (LoginUser) redisService.get(USER_TOKEN_KEY + username); 这种方法会报错
        // java.lang.ClassCastException: class com.alibaba.fastjson.JSONObject cannot be cast to class 。。。
        LoginUser loginUser = JSONObject.parseObject(redisService.get(USER_TOKEN_KEY + username).toString(), LoginUser.class);
        if (Objects.isNull(loginUser)) {
            throw new BizException("用户未登录");
        }
        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
