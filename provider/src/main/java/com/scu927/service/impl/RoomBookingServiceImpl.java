package com.scu927.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu927.common.Response;
import com.scu927.controller.request.CancelBookingRequest;
import com.scu927.controller.request.RoomBookingRequest;
import com.scu927.controller.response.RoomBookingDetailsResponse;
import com.scu927.entity.Room;
import com.scu927.entity.RoomBooking;
import com.scu927.mapper.RoomBookingMapper;
import com.scu927.mapper.RoomMapper;
import com.scu927.producer.EmailMessageProducer;
import com.scu927.service.RoomBookingService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private RoomBookingMapper roomBookingMapper;


    @Autowired
    private EmailMessageProducer messageProducer;

    @Override
    public Response<?> processBooking(RoomBookingRequest request) {
        try {


            // Check if booking date format is valid (YYYY-MM-DD)
            if (!isValidDateFormat(request.getBookingDate())) {
                return Response.error(400, "Invalid booking date format. Expected format: YYYY-MM-DD");
            }

            // Check if room is available
            List<Room> availableRooms = roomMapper.findAvailableRooms(request.getRoomGrade(), request.getBookingDate());
            if (!availableRooms.isEmpty()) {
                Room selectedRoom = availableRooms.get(0);  // Select the first available room
                RoomBooking booking = new RoomBooking();
                booking.setUsername(request.getUsername());
                booking.setRoomId(selectedRoom.getId());
                booking.setBookingDate(request.getBookingDate());
                booking.setName(request.getName());
                booking.setHomeAddress(request.getHomeAddress());
                booking.setPhoneNumber(request.getPhoneNumber());
                booking.setEmail(request.getEmail());
                booking.setRoomGrade(request.getRoomGrade());
                booking.setTotalAmount(selectedRoom.getPrice());

                booking.setPaymentStatus("UNPAID");


                // Save booking
                this.save(booking);
                Long bookingId = booking.getId(); // get booking id
                RoomBookingDetailsResponse bookingDetails = roomBookingMapper.getBookingDetailsById(bookingId);
                //generate message then send email
                String emailMessage = generateRoomBookingPaymentReminderEmail(bookingDetails);
                messageProducer.sendEmailMessage(emailMessage,"roomBookingQueue");



                return Response.success(bookingDetails)
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




    @Override
    public Response<?> cancelBooking(CancelBookingRequest request) {
        // 获取预定信息
        RoomBooking booking = roomBookingMapper.selectById(request.getId());

        if (booking == null) {
            return Response.success().setMessage("Booking not found" );
        }
        if (booking.getCancellationStatus().equals("CANCELLED")) {
            return Response.success().setMessage("The booking has been canceled" );
        }


        // get booking date
        LocalDate bookingDate = LocalDate.parse(booking.getBookingDate());
        LocalDateTime cancelDeadline = LocalDateTime.of(bookingDate, LocalTime.NOON); // 中午12点

        // get current date
        LocalDateTime currentTime = LocalDateTime.now();

        //Check whether the deadline is exceeded
        if (currentTime.isAfter(cancelDeadline)) {
            return Response.error(500,"Booking not found");

        }

        //update  cancellationStatus  as "CANCELLED"
        booking.setCancellationStatus("CANCELLED");
        booking.setCancellationReason(request.getCancellationReason());
        roomBookingMapper.updateById(booking);
        String emailMessage = generateRoomBookingCancellationEmail(booking);

        messageProducer.sendEmailMessage(emailMessage,"roomCancelReminderQueue");
        return Response.success("Booking cancelled successfully.");
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


    private String generateRoomBookingCancellationEmail(RoomBooking booking) {
        StringBuilder message = new StringBuilder();

        message.append("Email: ").append(booking.getEmail()).append(";\n\n")
                .append("Dear ").append(booking.getName()).append(",\n\n")
                .append("We regret to inform you that your room booking has been successfully cancelled. Below are the details of your cancelled booking:\n\n")
                .append("Booking ID: ").append(booking.getId()).append("\n")
                .append("Room Booking Date: ").append(booking.getBookingDate()).append("\n");



        message.append("We are sorry to see you cancel your booking. If you have any questions or need further assistance, please feel free to contact us.\n\n")
                .append("Best regards,\n")
                .append("The Room Booking Team");

        return message.toString();
    }

    private String generateRoomBookingPaymentReminderEmail(RoomBookingDetailsResponse bookingDetails) {
        StringBuilder message = new StringBuilder();

        // Email header and greeting
        message.append("Email: ").append(bookingDetails.getEmail()).append(";\n\n")
                .append("Dear ").append(bookingDetails.getName()).append(",\n\n");

        // Body: booking details and payment reminder
        message.append("This is a reminder that your room booking is awaiting payment. Below are the details of your booking:\n\n")
                .append("Booking ID: ").append(bookingDetails.getBookingId()).append("\n")
                .append("Room Booking Date: ").append(bookingDetails.getBookingDate()).append("\n")
                .append("Room Type: ").append(bookingDetails.getRoomGradeDetails()).append("\n")
                .append("Total Amount Due: ").append(bookingDetails.getTotalAmount()).append("\n");

        // Payment reminder closing message
        message.append("\nPlease complete the payment to confirm your booking. If you need further assistance, feel free to contact us.\n\n")
                .append("Best regards,\n")
                .append("The Room Booking Team");

        return message.toString();
    }

}