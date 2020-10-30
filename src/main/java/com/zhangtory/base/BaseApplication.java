package com.zhangtory.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 127.0.0.1:${port}/swagger-ui/index.html
 * @author ZhangTory
 */
@SpringBootApplication
@EnableOpenApi
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

}
