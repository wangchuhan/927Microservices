package com.scu927.controller;

import com.scu927.common.Response;
import com.scu927.controller.request.CancelBookingRequest;
import com.scu927.controller.request.PaymentRequest;
import com.scu927.controller.request.RoomBookingRequest;
import com.scu927.service.IPaymentService;
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

    @Autowired
    private IPaymentService paymentService;




    @PostMapping("/book")
    public Response<?> bookingTour(@RequestHeader("Authorization") String authorizationHeader,
                                    @RequestBody RoomBookingRequest request) {


        return roomBookingService.processBooking(authorizationHeader,request);
    }

    @PostMapping("/payment")
    public Response<?> payment(@RequestHeader("Authorization") String authorizationHeader,
                               @RequestBody PaymentRequest request) {
        return paymentService.processPayment(authorizationHeader,request);
    }


    @PostMapping("/cancel")
    public Response<?> cancelBooking(@RequestHeader("Authorization") String authorizationHeader,
                                     @RequestBody CancelBookingRequest request) {
        return roomBookingService.cancelBooking(authorizationHeader,request);
    }
}
