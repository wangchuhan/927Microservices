package com.scu927.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scu927.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chuhan
 * @date 2024/9/7
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}