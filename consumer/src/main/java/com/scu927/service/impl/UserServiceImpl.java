package com.scu927.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu927.entity.User;
import com.scu927.mapper.UserMapper;
import com.scu927.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chuhan
 * @date 2024/9/7
 */


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper; //  MyBatis-Plus   Mapper




    @Override
    public User selectByUsername(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName); //query condition：match by username
        // Execute query method
        return userMapper.selectOne(queryWrapper);

    }
}