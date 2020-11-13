package com.zhangtory.jwt.component;

import com.zhangtory.jwt.config.JwtConfig;
import com.zhangtory.jwt.exception.LoginCheckException;
import com.zhangtory.jwt.model.JwtUserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static com.zhangtory.jwt.constant.JwtConstant.TOKEN_EXPIRED;

/**
 * @Author: ZhangTory
 * @Date: 2020/11/13 10:21
 * @Description:
 */
public class JwtHelper {

    private JwtConfig jwtConfig;

    private Key key;

    public JwtHelper(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.key = Keys.hmacShaKeyFor(jwtConfig.secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 对用户颁发令牌
     * @param jwtUserVo
     * @return
     */
    public String createToken(JwtUserVo jwtUserVo) {
        return Jwts.builder()
                .signWith(this.key)
                .setIssuer(this.jwtConfig.jwtIssue)
                .setIssuedAt(new Date())
                .setId(jwtUserVo.getId().toString())
                .setSubject(jwtUserVo.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + this.jwtConfig.expiration))
                .compact();
    }

    /**
     * 获取请求用户的令牌内容
     * @param token
     * @return
     */
    public Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            // token过期或无效
            throw new LoginCheckException(TOKEN_EXPIRED);
        }
    }

    /**
     * 从令牌中获取id
     * @param token
     * @return
     */
    public String getId(String token) {
        return getTokenBody(token).getId();
    }

    /**
     * 从令牌中获取Subject
     * @param token
     * @return
     */
    public String getSubject(String token) {
        return getTokenBody(token).getSubject();
    }

}
