package com.zhangtory.base.core.exception;


import com.zhangtory.base.core.constant.CommonResult;

/**
 * @author zhangtory
 * @date 2021/3/20 13:56
 * @description: 签名异常
 */
public class SignException extends CommonException {

    public SignException(CommonResult result) {
        super(result);
    }

}
