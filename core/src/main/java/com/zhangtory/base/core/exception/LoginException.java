package com.zhangtory.base.core.exception;

import com.zhangtory.base.core.constant.CommonResult;

/**
 * @author zhangtory
 * @date 2021/5/29 15:54
 * @description:
 */
public class LoginException extends CommonException {
    public LoginException(CommonResult result) {
        super(result);
    }
}
