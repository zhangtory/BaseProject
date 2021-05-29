package com.zhangtory.sign.constant;

import com.zhangtory.base.core.constant.CommonResult;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/4 17:21
 * @Description:
 */
public class SignCommonResult extends CommonResult {

    public static final SignCommonResult TIMESTAMP_ERROR = new SignCommonResult(-201, "timestamp_error");
    public static final SignCommonResult SIGN_ERROR = new SignCommonResult(-202, "sign_error");

    public SignCommonResult(Integer code, String message) {
        super(code, message);
    }
}
