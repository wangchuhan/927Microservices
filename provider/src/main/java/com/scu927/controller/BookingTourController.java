package com.scu927.controller;

/**
 * @author Chuhan
 * @date 2024/9/8
 */

import com.scu927.common.Response;
import com.scu927.controller.request.BookingRequest;
import com.scu927.service.IBookingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingTourController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping("/create")
    public Response<?> createBooking(@RequestBody BookingRequest bookingRequest, HttpServletRequest httpServletRequest) {
        return bookingService.createBooking(bookingRequest, httpServletRequest);
    }
}