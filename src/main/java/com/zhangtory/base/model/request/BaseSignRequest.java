package com.zhangtory.base.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/29 18:21
 * @Description: 签名接口基础请求
 */
@Data
@ApiModel("签名接口基础请求")
public class BaseSignRequest {

    @ApiModelProperty("毫秒时间戳")
    @NotNull(message = "时间戳不能为空")
    private Long timestamp;

    @ApiModelProperty("签名")
    @NotBlank(message = "签名不能为空")
    private String sign;

}
