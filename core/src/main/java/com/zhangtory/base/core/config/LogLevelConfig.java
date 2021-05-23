package com.zhangtory.base.core.config;

import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author zhangtory
 * @date 2021/5/23 15:08
 * @description: 修改日志等级
 */
@Configuration
@Slf4j
@Order
public class LogLevelConfig implements ApplicationRunner {

    @Value("${logback.level}")
    private String level;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!StringUtils.isEmpty(level)) {
            LoggerContext loggerContext =(LoggerContext) LoggerFactory.getILoggerFactory();
            loggerContext.getLogger("root").setLevel(ch.qos.logback.classic.Level.toLevel(level));
            log.info("change logback level to: [{}]", loggerContext.getLogger("root").getLevel());
        }
    }
}
