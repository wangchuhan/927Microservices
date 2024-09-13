package com.scu927.service;

/**
 * @author Chuhan
 * @date 2024/9/8
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu927.common.Response;
import com.scu927.controller.request.BookingRequest;

import com.scu927.entity.TourBooking;
import jakarta.servlet.http.HttpServletRequest;

public interface IBookingService extends IService<TourBooking> {
    Response<?> createBooking(BookingRequest bookingRequest, HttpServletRequest httpServletRequest);
}