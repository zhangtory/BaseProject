package com.zhangtory.base.config;

import com.zhangtory.base.constant.CommonResult;
import com.zhangtory.base.exception.CommonException;
import com.zhangtory.base.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 10:28
 * @Description: 接口验签拦截器
 */
@Configuration
@Slf4j
public class SignCheckInterceptor implements HandlerInterceptor {

    /**
     * 签名值请求参数名称
     */
    private static final String SIGN_KEY = "sign";

    /**
     * 时间戳请求参数名称
     */
    private static final String TIMESTAMP = "timestamp";

    @Value("${secret}")
    private String secret;

    /**
     * 过期时间 60秒
     */
    private static final long TIME_OUT = 1000 * 60;

    /**
     * 验签: md5(k + v + ... + secret)
     * k按照ascii码排序
     * v为空时不参与验签
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 时间戳验证，防止重放攻击
        Long timestamp = 0L;
        try {
            timestamp = Long.parseLong(request.getParameter(TIMESTAMP));
        } catch (NumberFormatException e) {
            throw new CommonException(CommonResult.TIMESTAMP_ERROR);
        }
        if (Math.abs(timestamp - System.currentTimeMillis()) > TIME_OUT) {
            throw new CommonException(CommonResult.TIMESTAMP_ERROR);
        }
        // 签名值
        String sign = request.getParameter(SIGN_KEY);
        // 按照ascii码排序
        Map<String, String> sortMap = new TreeMap<>();
        parameterMap.forEach((k, v) -> {
            if (!SIGN_KEY.equals(k)) {
                sortMap.put(k, v[0]);
            }
        });
        StringBuffer originStr = new StringBuffer();
        sortMap.forEach((k, v) -> {
            if (StringUtils.isNotEmpty(v)) {
                originStr.append(k).append(v);
            }
        });
        originStr.append(secret);
        String md5 = EncryptUtils.md5(originStr.toString());
        if (!md5.equals(sign.toUpperCase())) {
            log.warn("签名错误，原串: [{}], md5: [{}], 收到的签名: [{}]", originStr.toString(), md5, sign);
            throw new CommonException(CommonResult.SIGN_ERROR);
        }
        return true;
    }

}
