package com.zhangtory.base.config;

import com.zhangtory.base.constant.BaseConstant;
import com.zhangtory.base.constant.CommonResult;
import com.zhangtory.base.exception.CommonException;
import com.zhangtory.base.utils.JwtUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangtory
 * @date 2019/12/12 21:00
 * @description: 用户登录检查拦截器
 */
@Configuration
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查是否登录
        String token = request.getHeader(BaseConstant.TOKEN_HEADER);
        if (StringUtils.isEmpty(token)) {
            throw new CommonException(CommonResult.USER_NOT_LOGIN);
        }
        // 检查token是否正确
        JwtUtils.getTokenBody(token);
        return true;
    }

}
