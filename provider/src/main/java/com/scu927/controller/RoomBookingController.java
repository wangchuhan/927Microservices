package com.scu927.controller;

import com.scu927.common.Response;
import com.scu927.controller.request.CancelBookingRequest;
import com.scu927.controller.request.PaymentRequest;
import com.scu927.controller.request.RoomBookingRequest;
import com.scu927.service.IPaymentService;
import com.scu927.service.RoomBookingService;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private IPaymentService paymentService;


    @PostMapping("/book")
    public Response<?> bookRoom(@RequestBody RoomBookingRequest request, HttpServletRequest httpServletRequest) {
        return roomBookingService.processBooking(request,httpServletRequest);
    }

    @PostMapping("/payment")
    public Response<?> payment(@RequestBody PaymentRequest request ,HttpServletRequest httpServletRequest) {

        return paymentService.processPayment(request,httpServletRequest);
    }


    @PostMapping("/cancel")
    public Response<?> cancelBooking(@RequestBody CancelBookingRequest request, HttpServletRequest httpServletRequest) {
        return roomBookingService.cancelBooking(request,httpServletRequest);
    }

}