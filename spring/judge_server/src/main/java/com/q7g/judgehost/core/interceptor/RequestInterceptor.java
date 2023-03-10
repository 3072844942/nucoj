package com.q7g.judgehost.core.interceptor;

import com.q7g.judgehost.core.configuration.AuthorizationConfiguration;
import com.q7g.judgehost.core.authorization.AuthorizationRequired;
import com.q7g.judgehost.exception.http.ForbiddenException;
import com.q7g.judgehost.utils.JudgeHolder;
import com.q7g.judgehost.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 请求拦截器
 *
 * @author yuzhanglong
 * @date 2020-7-2 8:34
 */
@EnableConfigurationProperties({AuthorizationConfiguration.class})
public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthorizationConfiguration authorizationConfiguration;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //  如果不是反射到方法
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 寻找 AuthorizationRequired注解
        AuthorizationRequired authorization = method.getAnnotation(AuthorizationRequired.class);
        if (authorization != null) {
            // 获取、解码accessToken
            String accessToken = request.getHeader("accessToken");
            // accessToken为空
            if (StringUtils.isEmpty(accessToken)) {
                throw new ForbiddenException("A0004");
            }
            String uid = authorizationConfiguration.getUserId();
            String secret = authorizationConfiguration.getSecretKey();
            Boolean isPass = TokenUtil.checkAuthToken(accessToken, uid, secret);
            if (!isPass) {
                throw new ForbiddenException("A0004");
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        JudgeHolder.removeThreadLocal();
    }
}
