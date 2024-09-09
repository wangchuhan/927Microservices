package com.scu927.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu927.entity.User;

import java.util.List;

/**
 * @author Chuhan
 * @date 2024/9/7
 */
public interface UserService extends IService<User> {
    // 你可以在这里定义额外的业务方法（如果有需要）

    User selectByUsername(String username);
}