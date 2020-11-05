package com.zhangtory.core.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 10:27
 * @Description: mvc config
 */
public class LogWebMvcConfig implements WebMvcConfigurer {

    private LogInterceptor logInterceptor;

    public LogWebMvcConfig(LogInterceptor logInterceptor) {
        this.logInterceptor = logInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
    }

}
