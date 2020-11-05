package com.zhangtory.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/5 10:31
 * @Description:
 */
@Configuration
@Import({GlobalExceptionHandler.class, CorsFilter.class})
public class CoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogWebMvcConfig logWebMvcConfig(LogInterceptor logInterceptor) {
        LogWebMvcConfig config = new LogWebMvcConfig(logInterceptor);
        return config;
    }

}
