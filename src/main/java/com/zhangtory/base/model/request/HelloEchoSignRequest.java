package com.zhangtory.base.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/29 18:21
 * @Description: echo
 */
@Data
@ApiModel("HelloEcho请求参数")
public class HelloEchoSignRequest extends BaseSignRequest {

    @ApiModelProperty("数据")
    @NotEmpty(message = "回显数据不能为空")
    private String content;

}
