package com.zhangtory.base.model.vo;

import lombok.Data;

/**
 * @Author: ZhangTory
 * @Date: 2020/10/30 16:11
 * @Description: jwt 用户信息
 */
@Data
public class JwtUserVo {

    private Long id;

    private String username;

    private String password;

}
