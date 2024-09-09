package com.scu927.mapper;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scu927.entity.Booking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookingMapper extends BaseMapper<Booking> {

    @Select("SELECT SUM(quantity) FROM Booking WHERE scenic_spot_id = #{scenicSpotId} AND booking_date = #{bookingDate} AND time_slot = #{timeSlot}")
    Integer getBookedQuantity(@Param("scenicSpotId") Long scenicSpotId,
                              @Param("bookingDate") String bookingDate,
                              @Param("timeSlot") String timeSlot);
}