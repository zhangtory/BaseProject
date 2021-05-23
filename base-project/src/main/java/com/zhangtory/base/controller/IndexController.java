package com.zhangtory.base.controller;

import com.zhangtory.base.core.response.BaseResponse;
import com.zhangtory.base.core.response.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangtory
 * @date 2021/5/22 21:11
 * @description:
 */
@RestController
@Api(tags = "首页")
public class IndexController {

    @PostMapping("/index")
    @ApiOperation(value = "首页")
    public BaseResponse<String> index(@RequestBody String body) {
        return ResponseBuilder.success("index" + body);
    }

}
