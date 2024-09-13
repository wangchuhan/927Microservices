package com.scu927.service.impl;

import com.scu927.client.ProviderClient;
import com.scu927.common.Response;
import com.scu927.config.JwtUtil;
import com.scu927.controller.request.CancelBookingRequest;
import com.scu927.controller.request.RoomBookingRequest;
import com.scu927.service.IRoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
@Service
public class RoomBookingImpl implements IRoomBookingService {

    @Autowired
    private ProviderClient providerClient;


    @Override
    public Response<?> processBooking(RoomBookingRequest request) {

        return providerClient.roomBooking(request);
    }

    @Override
    public Response<?> cancelBooking(CancelBookingRequest request) {
        return providerClient.cancelBooking(request);
    }


}
