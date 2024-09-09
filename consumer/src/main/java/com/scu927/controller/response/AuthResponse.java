package com.scu927.controller.response;

/**
 * @author Chuhan
 * @date 2024/9/7
 */
public class AuthResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthResponse(String token) {
        this.token = token;
    }
}
