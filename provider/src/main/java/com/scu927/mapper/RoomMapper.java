package com.scu927.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scu927.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {
    @Select("SELECT * FROM rooms WHERE room_grade = #{roomGrade} AND is_available = true AND id NOT IN " +
            "(SELECT room_id FROM room_booking WHERE booking_date = #{bookingDate})")
    List<Room> findAvailableRooms(@Param("roomGrade") String roomGrade, @Param("bookingDate") String bookingDate);
}