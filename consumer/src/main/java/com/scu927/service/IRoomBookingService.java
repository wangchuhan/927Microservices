package com.scu927.service;

import com.scu927.common.Response;
import com.scu927.controller.request.CancelBookingRequest;
import com.scu927.controller.request.RoomBookingRequest;


/**
 * @author Chuhan
 * @date 2024/9/9
 */
public interface IRoomBookingService {

    Response<?> processBooking(String authorizationHeader, RoomBookingRequest request);

    Response<?> cancelBooking(String authorizationHeader, CancelBookingRequest request);
}
