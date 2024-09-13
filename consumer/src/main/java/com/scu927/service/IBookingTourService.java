package com.scu927.service;

import com.scu927.common.Response;
import com.scu927.controller.request.TourBookingRequest;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
public interface IBookingTourService {

    Response<?> bookingTour(TourBookingRequest request);
}
