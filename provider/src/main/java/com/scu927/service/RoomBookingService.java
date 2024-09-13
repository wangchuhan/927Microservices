package com.scu927.service;

import com.scu927.common.Response;
import com.scu927.controller.request.CancelBookingRequest;
import com.scu927.controller.request.RoomBookingRequest;
import jakarta.servlet.http.HttpServletRequest;


/**
 * @author Chuhan
 * @date 2024/9/8
 */
public interface RoomBookingService {
    Response<?> processBooking(RoomBookingRequest request, HttpServletRequest httpServletRequest);

    Response<?> cancelBooking(CancelBookingRequest request, HttpServletRequest httpServletRequest);
}