package com.zhangtory.sign.config;

import com.zhangtory.sign.SignChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
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

    private SignChecker signChecker;

    public SignWebMvcConfig(SignCheckInterceptor signCheckInterceptor, SignChecker signChecker) {
        this.signCheckInterceptor = signCheckInterceptor;
        this.signChecker = signChecker;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("SignWebMvcConfig addInterceptors: signCheckInterceptor");
        // 验签检查拦截器
        InterceptorRegistration interceptor = registry.addInterceptor(signCheckInterceptor);
        // 放开swagger
        interceptor.excludePathPatterns("/swagger-resources/**", "/swagger-ui/**" , "/v3/**");
        if (signChecker.isCheck) {
            interceptor.addPathPatterns(signChecker.patterns);
        } else {
            interceptor.addPathPatterns("/**").excludePathPatterns(signChecker.patterns);
        }
    }

}
