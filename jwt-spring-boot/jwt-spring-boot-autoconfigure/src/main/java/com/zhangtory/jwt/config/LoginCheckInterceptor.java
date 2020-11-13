package com.zhangtory.jwt.config;

import com.zhangtory.jwt.component.JwtHelper;
import com.zhangtory.jwt.exception.LoginCheckException;
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

    private JwtHelper jwtHelper;

    public LoginCheckInterceptor(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查是否登录
        String token = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isEmpty(token)) {
            throw new LoginCheckException(USER_NOT_LOGIN);
        }
        // 检查token是否正确
        jwtHelper.getTokenBody(token);
        return true;
    }

}
