package com.zhangtory.core.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ZhangTory
 * @Date: 2020/9/3 15:13
 * @Description: 日志拦截器
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getMethod());
        log.info(request.getRequestURL().toString());
        log.info(JSONObject.toJSONString(request.getParameterMap()));
        return true;
    }
}
