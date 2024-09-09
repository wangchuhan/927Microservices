package com.scu927.controller;

import com.scu927.common.Response;
import com.scu927.controller.request.RoomBookingRequest;
import com.scu927.service.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
@RestController
@RequestMapping("/api/room-bookings")
public class RoomBookingController {

    @Autowired
    private RoomBookingService roomBookingService;

    @PostMapping("/book")
    public Response<?> bookRoom(@RequestBody RoomBookingRequest request) {
        return roomBookingService.processBooking(request);
    }
}