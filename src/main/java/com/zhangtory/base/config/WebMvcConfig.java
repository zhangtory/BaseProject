package com.zhangtory.base.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 10:27
 * @Description: mvc config
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private CheckSignInterceptor checkSignInterceptor;

    @Resource
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
        // TODO 验签开关
//        registry.addInterceptor(checkSignInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**" , "/v3/**");
    }
}
