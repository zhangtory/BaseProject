package com.zhangtory.jwt.config;

import com.zhangtory.jwt.component.UserContext;
import com.zhangtory.jwt.component.JwtHelper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/4 14:52
 * @Description:
 */
@Configuration
@AutoConfigureBefore(name = "jwtConfig")
public class JwtAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public JwtHelper jwtHelper(JwtConfig jwtConfig) {
        return new JwtHelper(jwtConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public UserContext userContext(JwtHelper jwtHelper) {
        return new UserContext(jwtHelper);
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginCheckInterceptor loginCheckInterceptor(JwtHelper jwtHelper) {
        return new LoginCheckInterceptor(jwtHelper);
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtWebMvcConfig jwtWebMvcConfig(LoginCheckInterceptor loginCheckInterceptor, JwtConfig jwtConfig) {
        JwtWebMvcConfig jwtWebMvcConfig = new JwtWebMvcConfig(loginCheckInterceptor, jwtConfig);
        return jwtWebMvcConfig;
    }

}
