package com.zhangtory.base.config;

import com.zhangtory.base.component.ResponseBuilder;
import com.zhangtory.base.constant.CommonResult;
import com.zhangtory.base.exception.CommonException;
import com.zhangtory.base.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/29 18:17
 * @Description: 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public BaseResponse commonException(CommonException e) {
        return ResponseBuilder.failure(e.getResult());
    }

    @ExceptionHandler(value = BindException.class)
    public BaseResponse bindException(BindException e) {
        return ResponseBuilder.failure(CommonResult.REQUEST_PARAMS_ERROR, e.getBindingResult().getAllErrors());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<Map<String, String>> errList = new ArrayList<>();
        errors.forEach(err -> {
            Map<String, String> errMap = new HashMap<>(4);
            errMap.put("errMsg", err.getDefaultMessage());
            DefaultMessageSourceResolvable source = (DefaultMessageSourceResolvable) err.getArguments()[0];
            errMap.put("filed", source.getDefaultMessage());
            errList.add(errMap);
        });
        return ResponseBuilder.failure(CommonResult.REQUEST_PARAMS_ERROR, errList);
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse globalExceptionHandler(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseBuilder.failure();
    }

}
