package com.zhangtory.jwt.config;

import com.zhangtory.jwt.UserContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/4 14:52
 * @Description:
 */
@Configuration
public class JwtAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LoginCheckInterceptor loginCheckInterceptor() {
        return new LoginCheckInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtWebMvcConfig jwtWebMvcConfig(LoginCheckInterceptor loginCheckInterceptor) {
        JwtWebMvcConfig jwtWebMvcConfig = new JwtWebMvcConfig(loginCheckInterceptor);
        return jwtWebMvcConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public UserContext userContext() {
        return new UserContext();
    }

}
