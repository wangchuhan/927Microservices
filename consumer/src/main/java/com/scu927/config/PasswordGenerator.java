package com.scu927.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @author Chuhan
 * @date 2024/9/7
 */
public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123"; // 这里可以替换成你想要加密的密码
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("原始密码: " + rawPassword);
        System.out.println("加密后的密码: " + encodedPassword);
    }

}
