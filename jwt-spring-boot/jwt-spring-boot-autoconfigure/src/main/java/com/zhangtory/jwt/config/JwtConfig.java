package com.zhangtory.jwt.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtory
 * @date 2020/11/8 16:05
 * @description: jwt初始化设置
 */
public abstract class JwtConfig {

    /**
     * 不需要jwt验证的接口列表
     */
    public List<String> patterns = new ArrayList<>();

    public JwtConfig() {
        initJwtConfig();
    }

    /**
     * jwt 秘钥
     */
    public String secretKey = "8d4646eb2d7067126eb08adb0672f7bb";

    /**
     * issue
     */
    public String jwtIssue = "issue";

    /**
     * 默认过期时间
     */
    public Long expiration = 7 * 24 * 60 * 60 * 1000L;

    /**
     * headers中提取token的字段名
     */
    public String tokenHeader = "Authorization";

    /**
     * 初始化设置
     */
    public abstract void initJwtConfig();

}
