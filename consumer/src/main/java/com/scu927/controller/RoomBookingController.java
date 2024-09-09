package com.scu927.controller;

import com.scu927.common.Response;
import com.scu927.controller.request.BookingTourRequest;
import com.scu927.controller.request.RoomBookingRequest;
import com.scu927.service.IBookingTourService;
import com.scu927.service.IRoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chuhan
 * @date 2024/9/9
 *
 *
 */
@RestController
@RequestMapping("/api/room-bookings")
public class RoomBookingController {

    @Autowired
    private IRoomBookingService roomBookingService;

    @PostMapping("/book")
    public Response<?> bookingTour(@RequestHeader("Authorization") String authorizationHeader,
                                    @RequestBody RoomBookingRequest request) {


        return roomBookingService.processBooking(authorizationHeader,request);
    }
}
