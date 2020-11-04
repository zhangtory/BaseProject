package com.zhangtory.sign.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: ZhangYaoYu
 * @Date: 2020/11/4 17:13
 * @Description:
 */
@Configuration
public class SignCheckerAutoConfiguration {

    @Value("${sign.secret}")
    private String secret;

    @Bean
    @ConditionalOnMissingBean
    public SignCheckInterceptor signCheckInterceptor() {
        return new SignCheckInterceptor(secret);
    }

    @Bean
    @ConditionalOnMissingBean
    public SignWebMvcConfig signWebMvcConfig(SignCheckInterceptor signCheckInterceptor) {
        SignWebMvcConfig config = new SignWebMvcConfig(signCheckInterceptor);
        return config;
    }

}
