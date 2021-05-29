package com.zhangtory.jwt.constant;

import com.zhangtory.base.core.constant.CommonResult;

/**
 * @author zhangtory
 * @date 2021/5/29 15:57
 * @description:
 */
public class JwtCommonResult extends CommonResult {

    public static final JwtCommonResult USER_NOT_LOGIN = new JwtCommonResult(-301, "user_not_login");
    public static final JwtCommonResult TOKEN_EXPIRED = new JwtCommonResult(-302, "token_expired");

    public JwtCommonResult(Integer code, String message) {
        super(code, message);
    }
}
