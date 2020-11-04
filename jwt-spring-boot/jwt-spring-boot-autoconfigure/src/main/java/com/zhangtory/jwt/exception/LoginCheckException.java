package com.zhangtory.jwt.exception;

import lombok.Data;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/4 14:52
 * @Description:
 */
@Data
public class LoginCheckException extends RuntimeException {

    public LoginCheckException(String message) {
        super(message);
    }

}
