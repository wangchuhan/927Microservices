package com.scu927.service;

import com.scu927.common.Response;
import com.scu927.controller.request.CancelBookingRequest;
import com.scu927.controller.request.RoomBookingRequest;


/**
 * @author Chuhan
 * @date 2024/9/9
 */
public interface IRoomBookingService {

    Response<?> processBooking(RoomBookingRequest request);

    Response<?> cancelBooking(CancelBookingRequest request);
}
