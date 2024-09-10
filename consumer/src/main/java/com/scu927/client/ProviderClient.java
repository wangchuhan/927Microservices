package com.scu927.client;

import com.scu927.common.Response;
import com.scu927.controller.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Chuhan
 * @date 2024/9/7
 */

// FeignClient
@FeignClient(name = "service-provider")
public interface ProviderClient {

    @PostMapping("/api/table-reservations/reserve")
    Response<?> reserveTable(@RequestBody TableReservationRequest request);


    @PostMapping("/api/bookings/create")
    Response<?> bookingTour(@RequestBody TourBookingRequest request);


    @PostMapping("/api/room-bookings/book")
    Response<?> roomBooking(@RequestBody RoomBookingRequest request);


    @PostMapping("/api/room-bookings/payment")
    Response<?> payment(@RequestBody PaymentRequest request);


    @PostMapping("/api/room-bookings/cancel")
    Response<?> cancelBooking(@RequestBody CancelBookingRequest request);
}