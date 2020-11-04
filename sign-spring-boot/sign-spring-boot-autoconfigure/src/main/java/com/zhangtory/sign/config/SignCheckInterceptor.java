package com.zhangtory.sign.config;

import com.zhangtory.sign.exception.SignException;
import com.zhangtory.sign.util.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

import static com.zhangtory.sign.constant.SignConstant.SIGN_ERROR;
import static com.zhangtory.sign.constant.SignConstant.TIMESTAMP_ERROR;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 10:28
 * @Description: 接口验签拦截器
 */
public class SignCheckInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(SignCheckInterceptor.class);

    /**
     * 签名值请求参数名称
     */
    private static final String SIGN_KEY = "sign";

    /**
     * 时间戳请求参数名称
     */
    private static final String TIMESTAMP = "timestamp";

    private String secret;

    public SignCheckInterceptor(String secret) {
        this.secret = secret;
    }

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
            throw new SignException(TIMESTAMP_ERROR);
        }
        if (Math.abs(timestamp - System.currentTimeMillis()) > TIME_OUT) {
            throw new SignException(TIMESTAMP_ERROR);
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
            logger.warn("签名错误，原串: [{}], md5: [{}], 收到的签名: [{}]", originStr.toString(), md5, sign);
            throw new SignException(SIGN_ERROR);
        }
        return true;
    }

}
