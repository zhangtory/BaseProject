package com.zhangtory.base.controller;

import com.zhangtory.base.model.request.HelloEchoSignRequest;
import com.zhangtory.core.response.BaseResponse;
import com.zhangtory.core.response.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/29 18:17
 * @Description: hello
 */
@RestController
@RequestMapping("hello")
@Api(tags = "逆向订单接口")
@Slf4j
public class HelloController {

    @PostMapping("/echo")
    @ApiOperation("回显")
    public BaseResponse applyInit(@Valid HelloEchoSignRequest request) {
        log.info("info test");
        log.error("error test");
        log.warn("warn test");
        log.debug("debug test");
        return ResponseBuilder.success(request.getContent());
    }

}
