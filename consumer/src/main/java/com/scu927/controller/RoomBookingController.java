package com.scu927.controller;

import com.scu927.common.Response;
import com.scu927.controller.request.CancelBookingRequest;
import com.scu927.controller.request.PaymentRequest;
import com.scu927.controller.request.RoomBookingRequest;
import com.scu927.service.IPaymentService;
import com.scu927.service.IRoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
@RestController
@RequestMapping("/api/room-bookings")
public class RoomBookingController {

    @Autowired
    private IRoomBookingService roomBookingService;

    @Autowired
    private IPaymentService paymentService;


    @PostMapping("/book")
    public Response<?> bookingTour(
            @RequestBody RoomBookingRequest request) {


        return roomBookingService.processBooking(request);
    }

    @PostMapping("/payment")
    public Response<?> payment(
            @RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }


    @PostMapping("/cancel")
    public Response<?> cancelBooking(
            @RequestBody CancelBookingRequest request) {
        return roomBookingService.cancelBooking(request);
    }
}
