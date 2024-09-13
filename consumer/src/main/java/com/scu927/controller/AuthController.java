package com.scu927.controller;

/**
 * @author Chuhan
 * @date 2024/9/7
 */

import com.scu927.config.JwtUtil;
import com.scu927.controller.request.AuthRequest;
import com.scu927.controller.response.AuthResponse;
import com.scu927.entity.User;
import com.scu927.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {


    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        User user = userService.selectByUsername(authRequest.getUsername());

        // validate username and password
        if (user != null && user.getPassword().equals(authRequest.getPassword())) {
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }


}

