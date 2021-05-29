package com.zhangtory.jwt.component;

import com.zhangtory.jwt.config.JwtConfig;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @author zhangtory
 * @date 2019/12/13 21:04
 * @description: 用户上下文，用于获取当前登录用户信息
 */
public class UserContext {

    private JwtHelper jwtHelper;

    private JwtConfig jwtConfig;

    public UserContext(JwtHelper jwtHelper, JwtConfig jwtConfig) {
        this.jwtHelper = jwtHelper;
        this.jwtConfig = jwtConfig;
    }

    public String getToken() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(jwtConfig.tokenHeader);
        return token;
    }

    public Long getUserId() {
        return Long.parseLong(jwtHelper.getId(getToken()));
    }

    public String getUsername() {
        return jwtHelper.getSubject(getToken());
    }

}
