package com.scu927.mapper;

import com.scu927.controller.response.ReservationDetailsResponse;
import com.scu927.entity.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
@Mapper
public interface TableMapper {

    @Select("SELECT * FROM tables WHERE restaurant_cafe_id = #{restaurantCafeId} AND capacity >= #{quantity} " +
            "AND id NOT IN (SELECT table_id FROM table_reservations WHERE reservation_date = #{reservationDate} AND time_slot = #{timeSlot})")
    List<Table> findAvailableTables(@Param("restaurantCafeId") Long restaurantCafeId,
                                    @Param("reservationDate") String reservationDate,
                                    @Param("timeSlot") String timeSlot,
                                    @Param("quantity") int quantity);

    @Select("SELECT tr.id AS reservationId, " +
            "rc.name AS restaurantName, " +
            "rc.location AS restaurantLocation, " +
            "t.table_number AS tableNumber, " +
            "t.location AS tableLocation " +
            "FROM table_reservations tr " +
            "JOIN tables t ON tr.table_id = t.id " +
            "JOIN restaurants_cafes rc ON t.restaurant_cafe_id = rc.id " +
            "WHERE tr.id = #{reservationId}")
    ReservationDetailsResponse getReservationDetails(Long reservationId);

}