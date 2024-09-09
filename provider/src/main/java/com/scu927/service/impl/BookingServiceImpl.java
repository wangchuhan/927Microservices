package com.scu927.service.impl;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
import com.scu927.common.Response;
import com.scu927.controller.request.BookingRequest;

import com.scu927.entity.Booking;
import com.scu927.mapper.BookingMapper;
import com.scu927.mapper.ScenicSpotMapper;
import com.scu927.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import static com.mysql.cj.conf.PropertyKey.logger;

@Service
public class BookingServiceImpl extends ServiceImpl<BookingMapper, Booking> implements IBookingService {

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    @Override
    public Response<Booking> createBooking(BookingRequest request) {
        try {
            Long scenicSpotId = request.getScenicSpotId();
            String username = request.getUsername();
            String email = request.getEmail();
            String bookingDate = request.getBookingDate();
            String timeSlot = request.getTimeSlot();
            int quantity = request.getQuantity();

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
            Booking booking = new Booking();
            booking.setScenicSpotId(scenicSpotId);
            booking.setUsername(username);
            booking.setEmail(email);
            booking.setBookingDate(bookingDate);
            booking.setTimeSlot(timeSlot);
            booking.setQuantity(quantity);

            // Save the booking information to the database
            int insertResult = bookingMapper.insert(booking);
            if (insertResult > 0) {
                return Response.success(booking);  // Return success with the created booking
            } else {
                // Return success with a custom message if the booking creation failed
                return Response.success("Booking creation failed");
            }
        } catch (Exception e) {
            // Log the error and return an error response for unexpected exceptions
            return Response.error(500, "server error " );
        }

    }
}