package com.zhangtory.sign.model;

import lombok.Data;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/29 18:21
 * @Description: 签名接口基础请求
 */
@Data
public class BaseSignRequest {

    private Long timestamp;

    private String sign;

}
