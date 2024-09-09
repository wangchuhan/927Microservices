package com.scu927.mapper;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
import com.scu927.entity.ScenicSpot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScenicSpotMapper {




    @Select("SELECT capacity FROM ScenicSpots WHERE id = #{scenicSpotId}")
    Integer getCapacityById(Long scenicSpotId);
}