package com.zhangtory.jwt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 10:27
 * @Description: mvc config
 */
public class JwtWebMvcConfig implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(JwtWebMvcConfig.class);

    private LoginCheckInterceptor loginCheckInterceptor;

    private JwtConfig jwtConfig;

    public JwtWebMvcConfig(LoginCheckInterceptor loginCheckInterceptor, JwtConfig jwtConfig) {
        this.loginCheckInterceptor = loginCheckInterceptor;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("JwtWebMvcConfig addInterceptors: loginCheckInterceptor");
        // 用户登录检查拦截器
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register")
                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**" , "/v3/**")
                .excludePathPatterns(jwtConfig.patterns);
    }
}
