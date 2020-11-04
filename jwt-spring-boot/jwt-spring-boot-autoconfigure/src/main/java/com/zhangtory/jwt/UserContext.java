package com.zhangtory.jwt;

import com.zhangtory.jwt.util.JwtUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.zhangtory.jwt.constant.JwtConstant.TOKEN_HEADER;

/**
 * @author zhangtory
 * @date 2019/12/13 21:04
 * @description: 用户上下文，用于获取当前登录用户信息
 */
public class UserContext {

    public static String getToken() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(TOKEN_HEADER);
        return token;
    }

    public static Long getUserId() {
        return Long.parseLong(JwtUtils.getId(getToken()));
    }

    public static String getUsername() {
        return JwtUtils.getSubject(getToken());
    }

}
