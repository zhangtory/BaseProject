package com.zhangtory.sign.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/4 17:30
 * @Description:
 */
public class SignWebMvcConfig implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(SignWebMvcConfig.class);

    private SignCheckInterceptor signCheckInterceptor;

    public SignWebMvcConfig(SignCheckInterceptor signCheckInterceptor) {
        this.signCheckInterceptor = signCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("SignWebMvcConfig addInterceptors: signCheckInterceptor");
        // 验签检查拦截器
        registry.addInterceptor(signCheckInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**" , "/v3/**");
    }

}
