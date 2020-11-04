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
    private SignCheckInterceptor signCheckInterceptor;

    @Resource
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
        // TODO 验签检查拦截器
//        registry.addInterceptor(signCheckInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**" , "/v3/**");
    }

}
