package com.zhangtory.sign;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 14:43
 * @Description: 签名需要的定制方法
 */
public abstract class SignChecker {

    /**
     * 接口列表
     */
    public List<String> patterns = new ArrayList<>();

    /**
     * 接口列表是否被检查
     */
    public Boolean isCheck = false;

    /**
     * 签名值请求参数名称
     */
    public String signKey = "sign";

    /**
     * 时间戳请求参数名称
     */
    public String timestamp = "timestamp";

    /**
     * 过期时间 60秒
     */
    public Long timeOut = 1000 * 60L;

    /**
     * 初始化拦截器，如果不使用默认约定，那么需要在这里对参数进行设置
     */
    public abstract void initCheckInterceptor();

    /**
     * 获取签名秘钥
     * @return
     */
    public abstract String getSecret();

}
