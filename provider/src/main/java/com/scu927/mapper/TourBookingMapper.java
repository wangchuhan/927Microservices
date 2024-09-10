package com.scu927.mapper;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scu927.controller.response.BookingDetailsResponse;
import com.scu927.entity.Booking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookingMapper extends BaseMapper<Booking> {

    @Select("SELECT SUM(quantity) FROM tour_booking WHERE scenic_spot_id = #{scenicSpotId} AND booking_date = #{bookingDate} AND time_slot = #{timeSlot}")
    Integer getBookedQuantity(@Param("scenicSpotId") Long scenicSpotId,
                              @Param("bookingDate") String bookingDate,
                              @Param("timeSlot") String timeSlot);

    @Select("SELECT b.id AS booking_id, b.booking_date, b.time_slot, b.quantity, b.username, b.email, " +
            "s.name AS scenic_spot_name, s.location AS scenic_spot_location, s.capacity AS scenic_spot_capacity, " +
            "s.description AS scenic_spot_description, s.price AS scenic_spot_price " +
            "FROM tour_booking b " +
            "JOIN scenicspots s ON b.scenic_spot_id = s.id " +
            "WHERE b.id = #{bookingId}")
    BookingDetailsResponse getBookingDetails(@Param("bookingId") Long bookingId);
}