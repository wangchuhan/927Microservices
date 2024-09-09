package com.scu927.client;

import com.scu927.common.Response;
import com.scu927.controller.request.BookingTourRequest;
import com.scu927.controller.request.RoomBookingRequest;
import com.scu927.controller.request.TableReservationRequest;
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
    Response<?> bookingTour(@RequestBody BookingTourRequest request);


    @PostMapping("/api/room-bookings/book")
    Response<?> roomBooking(@RequestBody RoomBookingRequest request);


}