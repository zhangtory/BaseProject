package com.zhangtory.jwt.config;

import com.zhangtory.base.core.exception.LoginException;
import com.zhangtory.jwt.component.JwtHelper;
import com.zhangtory.jwt.constant.JwtCommonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangtory
 * @date 2019/12/12 21:00
 * @description: 用户登录检查拦截器
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

    private JwtHelper jwtHelper;

    private JwtConfig jwtConfig;

    public LoginCheckInterceptor(JwtHelper jwtHelper, JwtConfig jwtConfig) {
        this.jwtHelper = jwtHelper;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查是否登录
        String token = request.getHeader(jwtConfig.tokenHeader);
        if (StringUtils.isEmpty(token)) {
            throw new LoginException(JwtCommonResult.USER_NOT_LOGIN);
        }
        // 检查token是否正确
        jwtHelper.getTokenBody(token);
        return true;
    }

}
