package com.zhangtory.base.core.exception;

import com.zhangtory.base.core.constant.CommonResult;
import lombok.Data;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 10:30
 * @Description: 通用异常
 */
@Data
public class CommonException extends RuntimeException {

    private CommonResult result;

    public CommonException(CommonResult result) {
        this.result = result;
    }

}
