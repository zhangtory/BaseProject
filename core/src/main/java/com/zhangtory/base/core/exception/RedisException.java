package com.zhangtory.base.core.exception;

import com.zhangtory.base.core.constant.CommonResult;

/**
 * @author zhangtory
 * @date 2021/3/20 13:56
 * @description: redis异常
 */
public class RedisException extends CommonException {

    public RedisException(CommonResult message) {
        super(message);
    }

}
