package com.zhangtory.core.response;

import com.zhangtory.core.constant.CommonResult;
import lombok.Getter;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 9:53
 * @Description: 统一返回对象
 */
@Getter
public class BaseResponse<T> {

    private int code;

    private String message;

    private T data;

    public BaseResponse(CommonResult result, T data) {
        this.code = result.getCode();
        this.message = result.getMessage();
        this.data = data;
    }

    public BaseResponse(CommonResult result) {
        this.code = result.getCode();
        this.message = result.getMessage();
    }

}
