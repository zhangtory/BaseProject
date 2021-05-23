package com.zhangtory.base.core.config;

import com.zhangtory.base.core.constant.CommonResult;
import com.zhangtory.base.core.exception.CommonException;
import com.zhangtory.base.core.response.BaseResponse;
import com.zhangtory.base.core.response.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.constraints.Null;
import java.util.*;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/29 18:17
 * @Description: 统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CommonException.class)
    public BaseResponse<Null> commonExceptionHandler(CommonException e) {
        return ResponseBuilder.failure(e.getResult());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse<List<Map<String, String>>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<Map<String, String>> errList = new ArrayList<>();
        errors.forEach(err -> {
            Map<String, String> errMap = new HashMap<>(4);
            errMap.put("errMsg", err.getDefaultMessage());
            DefaultMessageSourceResolvable source = (DefaultMessageSourceResolvable) Objects.requireNonNull(err.getArguments())[0];
            errMap.put("filed", source.getDefaultMessage());
            errList.add(errMap);
        });
        return ResponseBuilder.failure(CommonResult.REQUEST_PARAMS_ERROR, errList);
    }

    @ExceptionHandler(value = Exception.class)
    public BaseResponse<Null> globalExceptionHandler(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseBuilder.failure();
    }

}
