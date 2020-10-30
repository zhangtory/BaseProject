package com.zhangtory.base.controller;

import com.zhangtory.base.component.ResponseBuilder;
import com.zhangtory.base.model.request.HelloEchoSignRequest;
import com.zhangtory.base.model.response.BaseResponse;
import com.zhangtory.base.service.ILmRefundLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;


/**
*  @Author: ZhangTory
*  @Date: 2020-10-30
*  @Description:  控制层
*/
@RestController
@RequestMapping("log")
@Api(tags = "")
public class LmRefundLogController {

    @Autowired
    private ILmRefundLogService lmRefundLogService;

    @PostMapping("/get")
    @ApiOperation("回显")
    public BaseResponse applyInit() {
        return ResponseBuilder.success(lmRefundLogService.getById(11));
    }

}

