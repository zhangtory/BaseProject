package com.zhangtory.jwt.config;

import com.zhangtory.jwt.exception.LoginCheckException;
import com.zhangtory.jwt.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.zhangtory.jwt.constant.JwtConstant.TOKEN_HEADER;
import static com.zhangtory.jwt.constant.JwtConstant.USER_NOT_LOGIN;

/**
 * @author zhangtory
 * @date 2019/12/12 21:00
 * @description: 用户登录检查拦截器
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查是否登录
        String token = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isEmpty(token)) {
            throw new LoginCheckException(USER_NOT_LOGIN);
        }
        // 检查token是否正确
        JwtUtils.getTokenBody(token);
        return true;
    }

}
