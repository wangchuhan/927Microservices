package com.scu927.controller;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
import com.scu927.common.Response;
import com.scu927.controller.request.BookingRequest;
import com.scu927.entity.Booking;
import com.scu927.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping("/create")
    public Response<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {

       return bookingService.createBooking(bookingRequest);


    }
}