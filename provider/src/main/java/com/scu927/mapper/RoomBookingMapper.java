package com.scu927.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scu927.controller.response.RoomBookingDetailsResponse;
import com.scu927.entity.RoomBooking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
@Mapper
public interface RoomBookingMapper extends BaseMapper<RoomBooking> {

    @Select("SELECT " +
            "rb.id AS bookingId, " +
            "rb.booking_date AS bookingDate, " +
            "rb.name, " +
            "rb.home_address AS homeAddress, " +
            "rb.phone_number AS phoneNumber, " +
            "rb.email, " +
            "rb.room_grade AS roomGrade, " +
            "rb.total_amount AS totalAmount, " +
            "r.room_grade AS roomGradeDetails, " +
            "r.capacity AS roomCapacity, " +
            "r.price AS roomPrice, " +
            "r.is_available AS roomAvailability " +
            "FROM room_booking rb " +
            "LEFT JOIN rooms r ON rb.room_id = r.id " +
            "WHERE rb.id = #{bookingId}")
    RoomBookingDetailsResponse getBookingDetailsById(Long bookingId);


    @Update("UPDATE room_booking SET cancellation_status = 'CANCELLED' " +
            "WHERE payment_status = 'UNPAID' AND cancellation_status = 'NOT_CANCELLED' " +
            "AND TIMESTAMPDIFF(MINUTE, updated_at, NOW()) > 30")
    int cancelExpiredBookings();
}