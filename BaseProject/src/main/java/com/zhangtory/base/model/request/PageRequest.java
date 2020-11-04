package com.zhangtory.base.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 11:02
 * @Description: 分页查询 TODO 根据情况集成base验证请求
 */
@Data
public class PageRequest {

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "当前页数")
    private Integer pageNo = 1;

}
