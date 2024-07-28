package com.example.onlinefoodorderingsystem.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {

    // 创建BCryptPasswordEncoder实例
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 加密密码方法
    public static String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public static boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
