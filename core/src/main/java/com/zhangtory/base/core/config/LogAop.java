package com.zhangtory.base.core.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhangtory
 * @date 2021/5/22 22:59
 * @description: controller层日志打印
 */
@Aspect
@Component
@Slf4j
public class LogAop {

    @Pointcut("execution(public * com..controller.*Controller.*(..))")
    public void point(){}

    @Before("point()")
    public void before(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("==================================================");
        StringBuilder url = new StringBuilder(request.getRequestURL());
        if (request.getQueryString() != null) {
            url.append("?").append(request.getQueryString());
        }
        log.info("Method:[{}], URL:[{}]", request.getMethod(), url.toString());
        log.info("Exec Params:[{}]", jp.getArgs());
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info("process use [{}] ms", end - start);
        log.info("==================================================");
        return obj;
    }

    @AfterReturning(value = "point()", returning = "data")
    public void after(Object data) {
        log.info("Response:[{}]", JSONObject.toJSONString(data));
    }

}
