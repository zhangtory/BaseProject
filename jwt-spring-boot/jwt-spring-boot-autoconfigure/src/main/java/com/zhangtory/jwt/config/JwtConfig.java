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
     * 接口列表
     */
    public List<String> patterns = new ArrayList<>();

    public JwtConfig() {
        initJwtConfig();
    }

    /**
     * 初始化设置
     */
    public abstract void initJwtConfig();

}
