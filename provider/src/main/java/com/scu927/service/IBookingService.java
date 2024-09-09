package com.scu927.service;

/**
 * @author Chuhan
 * @date 2024/9/8
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu927.common.Response;
import com.scu927.controller.request.BookingRequest;

import com.scu927.entity.Booking;

public interface IBookingService extends IService<Booking> {
    Response<?> createBooking(BookingRequest bookingRequest);
}