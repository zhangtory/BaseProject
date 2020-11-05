package com.zhangtory.core.constant;

import lombok.Getter;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 9:56
 * @Description: 通用返回码
 */
@Getter
public class CommonResult {

    public static final CommonResult SUCCESS = new CommonResult(0, "success");

    public static final CommonResult SYSTEM_ERROR = new CommonResult(0, "system_error");

    public static final CommonResult REQUEST_PARAMS_ERROR = new CommonResult(-100, "request_params_error");

    public static final CommonResult TIMESTAMP_ERROR = new CommonResult(-101, "timestamp_error");

    public static final CommonResult SIGN_ERROR = new CommonResult(-103, "sign_error");

    public static final CommonResult TOKEN_EXPIRED = new CommonResult(-104, "token_expired");

    public static final CommonResult USER_NOT_LOGIN = new CommonResult(-104, "user_not_login");


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
