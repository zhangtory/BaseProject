package com.zhangtory.jwt.config;

import com.zhangtory.jwt.component.UserContext;
import com.zhangtory.jwt.component.JwtHelper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/4 14:52
 * @Description:
 */
@Configuration
@AutoConfigureAfter(name = "jwtConfig")
public class JwtAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(name = "jwtConfig")
    public JwtHelper jwtHelper(JwtConfig jwtConfig) {
        return new JwtHelper(jwtConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(name = {"jwtHelper", "jwtConfig"})
    public UserContext userContext(JwtHelper jwtHelper, JwtConfig jwtConfig) {
        return new UserContext(jwtHelper, jwtConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(name = {"jwtHelper", "jwtConfig"})
    public LoginCheckInterceptor loginCheckInterceptor(JwtHelper jwtHelper, JwtConfig jwtConfig) {
        return new LoginCheckInterceptor(jwtHelper, jwtConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(name = {"jwtConfig", "loginCheckInterceptor"})
    public JwtWebMvcConfig jwtWebMvcConfig(LoginCheckInterceptor loginCheckInterceptor, JwtConfig jwtConfig) {
        JwtWebMvcConfig jwtWebMvcConfig = new JwtWebMvcConfig(loginCheckInterceptor, jwtConfig);
        return jwtWebMvcConfig;
    }

}
