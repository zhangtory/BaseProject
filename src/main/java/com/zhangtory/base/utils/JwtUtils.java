package com.zhangtory.base.utils;

import com.zhangtory.base.constant.CommonResult;
import com.zhangtory.base.exception.CommonException;
import com.zhangtory.base.model.vo.JwtUserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * @author zhangtory
 * @date 2019/12/10 21:23
 * @description: json_web_token 工具
 */
public class JwtUtils {

    // TODO 设置issuer
    public static final String JWT_ISSUER = "WayHome_Server_0.3";

    public static final Long DEFAULT_EXPIRATION = 7 * 24 * 60 * 60 * 1000L;

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 对用户颁发令牌
     * @param jwtUserVo
     * @return
     */
    public static String createToken(JwtUserVo jwtUserVo) {
        return Jwts.builder()
                .signWith(KEY)
                .setIssuer(JWT_ISSUER)
                .setIssuedAt(new Date())
                .setId(jwtUserVo.getId().toString())
                .setSubject(jwtUserVo.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + DEFAULT_EXPIRATION))
                .compact();
    }

    /**
     * 获取请求用户的令牌内容
     * @param token
     * @return
     */
    public static Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            // token过期或无效
            throw new CommonException(CommonResult.TOKEN_EXPIRED);
        }
    }

    /**
     * 从令牌中获取id
     * @param token
     * @return
     */
    public static String getId(String token) {
        return getTokenBody(token).getId();
    }

    /**
     * 从令牌中获取Subject
     * @param token
     * @return
     */
    public static String getSubject(String token) {
        return getTokenBody(token).getSubject();
    }

}
