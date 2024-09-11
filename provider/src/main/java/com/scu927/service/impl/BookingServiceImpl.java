package com.scu927.service.impl;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
import com.scu927.common.Response;
import com.scu927.controller.request.BookingRequest;

import com.scu927.controller.response.BookingDetailsResponse;
import com.scu927.entity.TourBooking;
import com.scu927.entity.TourBooking;
import com.scu927.mapper.TourBookingMapper;
import com.scu927.mapper.ScenicSpotMapper;
import com.scu927.producer.EmailMessageProducer;

import com.scu927.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service
public class BookingServiceImpl extends ServiceImpl<TourBookingMapper, TourBooking> implements IBookingService {

    @Autowired
    private TourBookingMapper bookingMapper;

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    @Autowired
    private EmailMessageProducer messageProducer;
    @Override
    public Response<?> createBooking(BookingRequest request) {
        try {
            Long scenicSpotId = request.getScenicSpotId();
            String username = request.getUsername();
            String email = request.getEmail();
            String bookingDate = request.getBookingDate();
            String timeSlot = request.getTimeSlot();
            int quantity = request.getQuantity();
            String name =  request.getName();
            String phoneNumber =  request.getPhoneNumber();
            // Retrieve the capacity of the scenic spot
            Integer capacity = scenicSpotMapper.getCapacityById(scenicSpotId);
            if (capacity == null) {
                // Return success with a custom message if the scenic spot is not found
                return Response.success("Scenic spot not found");
            }

            // Retrieve the number of bookings for the selected date and time slot
            Integer bookedQuantity = bookingMapper.getBookedQuantity(scenicSpotId, bookingDate, timeSlot);
            if (bookedQuantity == null) {
                bookedQuantity = 0;  // Set to 0 if no bookings exist
            }

            // Check if there is enough capacity for the booking
            if (bookedQuantity + quantity > capacity) {
                // Return success with a custom message if the time slot is fully booked
                return Response.success("The selected time slot is fully booked");
            }

            // Create a new Booking object
            TourBooking booking = new TourBooking();
            booking.setScenicSpotId(scenicSpotId);
            booking.setUsername(username);
            booking.setEmail(email);
            booking.setBookingDate(bookingDate);
            booking.setTimeSlot(timeSlot);
            booking.setQuantity(quantity);
            booking.setName(name);
            booking.setPhoneNumber(phoneNumber);

            // Save the booking information to the database
            int insertResult = bookingMapper.insert(booking);
            if (insertResult > 0) {
                BookingDetailsResponse bookingDetails = bookingMapper.getBookingDetails(booking.getId());

                String emailMessage = generateBookingConfirmationEmail(bookingDetails);
                // Send the message to the RabbitMQ queue
                messageProducer.sendEmailMessage(emailMessage,"scenicSpotQueue");
                return Response.success(bookingDetails);  // Return success with the created booking
            } else {
                // Return success with a custom message if the booking creation failed
                return Response.success("Booking creation failed");
            }
        } catch (Exception e) {
            // Log the error and return an error response for unexpected exceptions
            return Response.error(500, "server error " );
        }

    }
    private String generateBookingConfirmationEmail(BookingDetailsResponse bookingDetails){
        StringBuilder message = new StringBuilder();

        message.append("Email: ").append(bookingDetails.getEmail()).append(";\n\n")
                .append("Dear ").append(bookingDetails.getUsername()).append(",\n\n")
                .append("Thank you for choosing our service! We are pleased to confirm that your reservation has been successfully made. Here are the details of your reservation:\n\n")
                .append("Reservation Number: ").append(bookingDetails.getBookingId()).append("\n")
                .append("Scenic Spot: ").append(bookingDetails.getScenicSpotName()).append("\n")
                .append("Location: ").append(bookingDetails.getScenicSpotLocation()).append("\n")
                .append("Reservation Date: ").append(bookingDetails.getBookingDate()).append("\n")
                .append("Time Slot: ").append(bookingDetails.getTimeSlot()).append("\n")
                .append("Number of Guests: ").append(bookingDetails.getQuantity()).append("\n")
                .append("Price: ").append(bookingDetails.getScenicSpotPrice()).append("\n\n")
                .append("We look forward to welcoming you on ").append(bookingDetails.getBookingDate()).append(". If you have any questions or need further assistance, please feel free to contact us.\n\n")
                .append("Best regards,\n")
                .append("The Scenic Spot Reservation Team");

        String finalMessage = message.toString();

        return finalMessage;
    }

}