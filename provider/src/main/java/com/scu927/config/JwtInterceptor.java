package com.scu927.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

/**
 * @author Chuhan
 * @date 2024/9/13
 */

public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            String authorizationHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
            String email = null;
            String name = null;
            String phoneNumber = null;


            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7); // Remove "Bearer " prefix
                username = jwtUtil.extractUsername(token);
                email = jwtUtil.extractEmail(token);
                name = jwtUtil.extractName(token);
                phoneNumber= jwtUtil.extractPhoneNumber(token);
            }

            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("name", name);
            request.setAttribute("phoneNumber", phoneNumber);
        }

        return true;
    }

}