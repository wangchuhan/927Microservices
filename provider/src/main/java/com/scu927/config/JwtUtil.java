package com.scu927.config;

/**
 * @author Chuhan
 * @date 2024/9/7
 */
import com.scu927.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component

public class JwtUtil {
    private final String privateKey="weAXnlH9N59IXifWhAvwm/YWymNPUEKW3uQIp6CVI4k=";
    SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(privateKey));
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10hours
              //  .signWith(SignatureAlgorithm.RS256, privateKey)  // use private sign
               .signWith(key)  // URL safe code
                .compact(); // generate Base64 URL Safe  JWT
    }

    // from JWT Token get username
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // get all field from token（claims）
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody(); // dealing with URL Safe Base64 while parsing
    }

    //validate JWT Token
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