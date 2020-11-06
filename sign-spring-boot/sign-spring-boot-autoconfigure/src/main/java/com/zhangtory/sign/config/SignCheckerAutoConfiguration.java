package com.zhangtory.sign.config;

import com.zhangtory.sign.SignChecker;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/4 17:13
 * @Description:
 */
@Configuration
@AutoConfigureBefore(name = "signChecker")
public class SignCheckerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SignCheckInterceptor signCheckInterceptor(SignChecker signChecker) {
        return new SignCheckInterceptor(signChecker);
    }

    @Bean
    @ConditionalOnMissingBean
    public SignWebMvcConfig signWebMvcConfig(SignCheckInterceptor signCheckInterceptor, SignChecker signChecker) {
        SignWebMvcConfig config = new SignWebMvcConfig(signCheckInterceptor, signChecker);
        return config;
    }

}
