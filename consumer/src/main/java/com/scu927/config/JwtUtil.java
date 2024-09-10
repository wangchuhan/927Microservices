package com.scu927.config;

/**
 * @author Chuhan
 * @date 2024/9/7
 */
import com.scu927.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component

public class JwtUtil {


    private final String privateKey="weAXnlH9N59IXifWhAvwm/YWymNPUEKW3uQIp6CVI4k=";

    //private String SECRET_KEY = "u8gfh9sebsj34rk5fgfui56ujp54fnvh";
    //SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(privateKey));


    // 生成 JWT Token
    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("name", user.getName());
        claims.put("phoneNumber", user.getPhoneNumber());
        claims.put("email", user.getEmail());
        return createToken(claims, user.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10小时
              //  .signWith(SignatureAlgorithm.RS256, privateKey)  // 使用私钥签名
               .signWith(key)  // URL 安全编码
                .compact(); // 默认会生成 Base64 URL Safe 编码的 JWT
    }

    // 从 JWT Token 中解析出用户名
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }



    // 解析所有的声明（claims）
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody(); // 解析的时候也会自动处理 URL Safe Base64 编码
    }

    // 验证 JWT Token
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // 判断 Token 是否过期
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }



    // Extract name from JWT Token
    public String extractName(String token) {
        return extractAllClaims(token).get("name", String.class);
    }

    // Extract contact number from JWT Token
    public String extractPhoneNumber(String token) {
        return extractAllClaims(token).get("phoneNumber", String.class);
    }

    // Extract email from JWT Token
    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }
}