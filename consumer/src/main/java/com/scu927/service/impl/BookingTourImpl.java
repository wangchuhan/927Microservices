package com.scu927.service.impl;

import com.scu927.client.ProviderClient;
import com.scu927.common.Response;
import com.scu927.config.JwtUtil;
import com.scu927.controller.request.BookingTourRequest;
import com.scu927.service.IBookingTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chuhan
 * @date 2024/9/7
 */


@Service
public class BookingTourImpl implements IBookingTourService {

    @Autowired
    private ProviderClient providerClient;

    @Autowired
    private JwtUtil jwtUtil;  // Inject the JwtTokenUtil

    @Override
    public Response<?> bookingTour(String authorizationHeader, BookingTourRequest request) {
        // extract Token，from Authorization header get Bearer Token
        String token = extractTokenFromHeader(authorizationHeader);
        // Parse the token to get user information
        String username = jwtUtil.extractUsername(token);
        String name = jwtUtil.extractName(token);
        String phoneNumber = jwtUtil.extractPhoneNumber(token);
        String email = jwtUtil.extractEmail(token);

        // Set the user information into the request
        request.setUsername(username);
        request.setName(name);
        request.setPhoneNumber(phoneNumber);
        request.setEmail(email);
        return providerClient.bookingTour(request);
    }

    // from Authorization header get JWT Token
    private String extractTokenFromHeader(String authorizationHeader) {
        // 检查 Authorization 头部是否以 "Bearer " 开头
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);  // 提取 "Bearer " 之后的部分，即真正的 Token
        } else {
            throw new IllegalArgumentException("Invalid Authorization header format.");
        }
    }
}