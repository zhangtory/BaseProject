package com.zhangtory.redis.exception;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/6 11:01
 * @Description:
 */
public class RedisException extends RuntimeException {

    public RedisException(String message) {
        super(message);
    }

}
