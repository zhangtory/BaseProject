package com.zhangtory.core.util;

import java.util.Random;

/**
 * @author zhangtory
 * @date 2019/12/7 20:17
 * @description: 用户密码工具
 */
public class PasswordUtils {

    /**
     * 密码MD5时盐的长度
     */
    public static final int PASSWORD_SALT_LEN = 6;

    /**
     * hash值长度
     */
    public static final int HASH_LENGTH = 32;

    /**
     * 密码加密
     * @param password 明文密码
     * @return
     */
    public static String getEncryptedPassword(String password) {
        Random random = new Random();
        StringBuilder salt = new StringBuilder(String.valueOf(random.nextInt(999999)));
        while (salt.toString().length() < PASSWORD_SALT_LEN) {
            salt.append("0");
        }
        String encode = EncryptUtils.md5(password + salt.toString());
        return encode + salt.toString();
    }

    /**
     * 验证密码是否正确
     * @param password 明文密码
     * @param encryptedPassword 加密密码字符串
     * @return
     */
    public static boolean checkPassword(String password, String encryptedPassword) {
        if (encryptedPassword == null || encryptedPassword.length() != PASSWORD_SALT_LEN + HASH_LENGTH) {
            return false;
        }
        encryptedPassword = encryptedPassword.toUpperCase();
        String salt = encryptedPassword.substring(HASH_LENGTH);
        String encode = EncryptUtils.md5(password + salt) + salt;
        return encode.equals(encryptedPassword);
    }

}
