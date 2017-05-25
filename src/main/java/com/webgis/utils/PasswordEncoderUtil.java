package com.webgis.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/18.
 */
public class PasswordEncoderUtil {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    PasswordEncoderUtil(){
        throw new AssertionError("工具类不能被实例化！");
    }

    /**
     * 密码匹配
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public static boolean checkValid(String rawPassword, String encodedPassword){
        if (rawPassword == null || encodedPassword == null){
            throw new NullPointerException();
        }
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 密码加密
     * @param rawPassword
     * @return
     */
    public static String encode(String rawPassword){
        if (rawPassword == null){
            throw new NullPointerException();
        }
        return passwordEncoder.encode(rawPassword);
    }
}
