package com.zhangtory.base.core.constant;

import lombok.Getter;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 9:56
 * @Description: 通用返回码
 */
@Getter
public class CommonResult {

    public static final CommonResult SUCCESS = new CommonResult(0, "success");

    public static final CommonResult SYSTEM_ERROR = new CommonResult(-1, "system_error");

    public static final CommonResult REQUEST_PARAMS_ERROR = new CommonResult(-100, "request_params_error");


    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 异常描述
     */
    private final String message;

    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
