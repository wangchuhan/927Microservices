package com.scu927.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu927.entity.User;

import java.util.List;

/**
 * @author Chuhan
 * @date 2024/9/7
 */
public interface UserService extends IService<User> {
    // Custom functions can be defined here (if needed)

    User selectByUsername(String username);
}