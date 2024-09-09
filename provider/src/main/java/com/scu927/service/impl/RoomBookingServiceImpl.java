package com.scu927.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu927.common.Response;
import com.scu927.controller.request.RoomBookingRequest;
import com.scu927.controller.response.RoomBookingResponse;
import com.scu927.entity.Room;
import com.scu927.entity.RoomBooking;
import com.scu927.mapper.RoomBookingMapper;
import com.scu927.mapper.RoomMapper;
import com.scu927.service.RoomBookingService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
@Service
public class RoomBookingServiceImpl extends ServiceImpl<RoomBookingMapper, RoomBooking> implements RoomBookingService {

    @Autowired
    private RoomMapper roomMapper;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Response<?> processBooking(RoomBookingRequest request) {
        try {
            // Validate inputs
            if (isEmpty(request.getName()) || isEmpty(request.getHomeAddress()) ||
                    isEmpty(request.getContactNumber()) || isEmpty(request.getEmail()) ||
                    isEmpty(request.getBookingDate()) || isEmpty(request.getRoomGrade())) {
                return Response.error(400, "All fields must be filled out");
            }
            // Check if booking date format is valid (YYYY-MM-DD)
            if (!isValidDateFormat(request.getBookingDate())) {
                return Response.error(400, "Invalid booking date format. Expected format: YYYY-MM-DD");
            }

            // Check if room is available
            List<Room> availableRooms = roomMapper.findAvailableRooms(request.getRoomGrade(), request.getBookingDate());
            if (!availableRooms.isEmpty()) {
                Room selectedRoom = availableRooms.get(0);  // Select the first available room
                RoomBooking booking = new RoomBooking();
                booking.setRoomId(selectedRoom.getId());
                booking.setBookingDate(request.getBookingDate());
                booking.setName(request.getName());
                booking.setHomeAddress(request.getHomeAddress());
                booking.setContactNumber(request.getContactNumber());
                booking.setEmail(request.getEmail());
                booking.setRoomGrade(request.getRoomGrade());
                booking.setTotalAmount(selectedRoom.getPrice());

                // Save booking
                this.save(booking);


                return Response.success(booking)
                        .setMessage("Booking confirmed with room grade: " + request.getRoomGrade());
            } else {
                // Suggest alternative rooms
                String alternativeRoomGrade = suggestAlternativeRoom(request.getRoomGrade());
                return Response.success()
                        .setMessage("Requested room unavailable, alternative suggestion: " + alternativeRoomGrade);
            }
        } catch (Exception e) {
            return Response.error(500, "An unexpected error occurred: " + e.getMessage());
        }
    }

    // Helper methods for input validation and alternative room suggestion
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private String suggestAlternativeRoom(String roomGrade) {
        // Suggest alternative room based on availability
        if (roomGrade.equals("Standard")) {
            return "Deluxe";
        } else if (roomGrade.equals("Deluxe")) {
            return "Suite";
        } else {
            return "Standard";
        }
    }

    // Helper method to validate the booking date format (YYYY-MM-DD)
    private boolean isValidDateFormat(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

}