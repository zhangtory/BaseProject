package com.zhangtory.core.exception;

/**
 * @author zhangtory
 * @date 2021/3/20 13:56
 * @description: redis异常
 */
public class RedisException extends RuntimeException {

    public RedisException(String message) {
        super(message);
    }

}
