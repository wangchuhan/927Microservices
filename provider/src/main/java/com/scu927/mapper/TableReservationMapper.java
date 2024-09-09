package com.scu927.mapper;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scu927.controller.response.TableRecommendationResponse;
import com.scu927.entity.TableReservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TableReservationMapper extends BaseMapper<TableReservation> {
    // Additional complex queries can be added here if needed
    @Select("SELECT DISTINCT ts.time_slot, " +
            "COUNT(t.id) AS available_tables " +
            "FROM (SELECT '08:00-10:00' AS time_slot " +
            "      UNION SELECT '10:00-12:00' " +
            "      UNION SELECT '12:00-14:00' " +
            "      UNION SELECT '14:00-16:00' " +
            "      UNION SELECT '16:00-18:00') AS ts " +
            "LEFT JOIN tables t ON t.restaurant_cafe_id = #{restaurantCafeId} AND t.capacity >= #{quantity} " +
            "LEFT JOIN table_reservations tr ON ts.time_slot = tr.time_slot " +
            "AND tr.reservation_date = #{reservationDate} " +
            "AND t.id = tr.table_id " +
            "WHERE tr.time_slot IS NULL " +
            "GROUP BY ts.time_slot")
    List<TableRecommendationResponse> findAlternativeTimeSlots(@Param("restaurantCafeId") Long restaurantCafeId,
                                                               @Param("reservationDate") String reservationDate,
                                                               @Param("quantity") int quantity);
}