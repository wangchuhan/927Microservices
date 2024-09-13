package com.scu927.service.impl;

import com.scu927.client.ProviderClient;
import com.scu927.common.Response;
import com.scu927.config.JwtUtil;
import com.scu927.controller.request.TourBookingRequest;
import com.scu927.service.IBookingTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chuhan
 * @date 2024/9/7
 */


@Service
public class BookingTourImpl implements IBookingTourService {

    @Autowired
    private ProviderClient providerClient;


    @Override
    public Response<?> bookingTour(TourBookingRequest request) {

        return providerClient.bookingTour(request);
    }

}