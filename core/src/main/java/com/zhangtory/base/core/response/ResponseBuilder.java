package com.zhangtory.base.core.response;


import com.zhangtory.base.core.constant.CommonResult;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 10:01
 * @Description: 统一返回包装
 */
public class ResponseBuilder {

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(CommonResult.SUCCESS);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(CommonResult.SUCCESS, data);
    }

    public static <T> BaseResponse<T> success(CommonResult result, T data) {
        return new BaseResponse<>(result, data);
    }

    public static <T> BaseResponse<T> failure() {
        return new BaseResponse<>(CommonResult.SYSTEM_ERROR);
    }

    public static <T> BaseResponse<T> failure(CommonResult result) {
        return new BaseResponse<>(result);
    }

    public static <T> BaseResponse<T> failure(CommonResult result, T data) {
        return new BaseResponse<>(result, data);
    }
}
