package com.scu927.service.impl;

import com.scu927.client.ProviderClient;
import com.scu927.common.Response;
import com.scu927.config.JwtUtil;
import com.scu927.controller.request.PaymentRequest;

import com.scu927.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author Chuhan
 * @date 2024/9/9
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private ProviderClient providerClient;

    @Autowired
    private JwtUtil jwtUtil;  // Inject the JwtTokenUtil

    @Override
    public Response<?> processPayment(String authorizationHeader,PaymentRequest request) {
        // extract Token，from Authorization header get Bearer Token
        String token = extractTokenFromHeader(authorizationHeader);
        // Parse the token to get user information
        String username = jwtUtil.extractUsername(token);

        String email = jwtUtil.extractEmail(token);

        // Set the user information into the request
        request.setUsername(username);

        request.setEmail(email);
        return providerClient.payment(request);
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        // check Authorization wether  "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);  // get  the char after "Bearer " ，that is Token
        } else {
            throw new IllegalArgumentException("Invalid Authorization header format.");
        }
    }
}