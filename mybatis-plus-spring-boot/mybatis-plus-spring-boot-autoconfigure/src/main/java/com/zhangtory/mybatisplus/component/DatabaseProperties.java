package com.zhangtory.mybatisplus.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/5 9:26
 * @Description:
 */
@ConfigurationProperties("spring.datasource")
@Data
public class DatabaseProperties {

    private String driverClassName;

    private String username;

    private String password;

    private String url;

    private String type;

}
