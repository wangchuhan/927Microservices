package com.scu927.controller;

import com.scu927.common.Response;
import com.scu927.controller.request.TourBookingRequest;
import com.scu927.service.IBookingTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chuhan
 * @date 2024/9/9
 *
 *
 */
@RestController
@RequestMapping("/api/bookingTour")
public class BookingTourController {

    @Autowired
    private IBookingTourService bookingTourService;

    @PostMapping("/create")
    public Response<?> bookingTour(
                                    @RequestBody TourBookingRequest request) {


        return bookingTourService.bookingTour(request);
    }
}
