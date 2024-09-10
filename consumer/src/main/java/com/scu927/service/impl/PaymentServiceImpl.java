package com.scu927.service.impl;

import com.scu927.common.Response;
import com.scu927.controller.request.PaymentRequest;
import com.scu927.controller.response.RoomBookingDetailsResponse;
import com.scu927.entity.RoomBooking;
import com.scu927.entity.RoomBookingPayment;
import com.scu927.mapper.RoomBookingMapper;
import com.scu927.mapper.RoomBookingPaymentMapper;
import com.scu927.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chuhan
 * @date 2024/9/9
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private RoomBookingMapper roomBookingMapper;

    @Autowired
    private RoomBookingPaymentMapper roomBookingPaymentMapper;

    @Override
    public Response<?> processPayment(PaymentRequest paymentRequest) {
        // get teh row by bookingId
        RoomBooking booking = roomBookingMapper.selectById(paymentRequest.getRoomBookingId());

        if (booking != null && booking.getPaymentStatus().equals("UNPAID")) {
            // Simulating Successful Payment Logic
            boolean paymentSuccess = simulatePayment(paymentRequest);
            if (paymentSuccess) {

                Date currentDate = new Date();
                // save payment information to table room_booking_payments
                RoomBookingPayment payment = new RoomBookingPayment();
                payment.setRoomBookingId(paymentRequest.getRoomBookingId());
                payment.setRoomPaymentAmount(paymentRequest.getRoomPaymentAmount());
                payment.setPaymentMethod(paymentRequest.getPaymentMethod());
                payment.setPaymentStatus("PAID");
                payment.setPaymentDate(new SimpleDateFormat("yyyy-MM-dd HH:DD").format(currentDate));
                roomBookingPaymentMapper.insert(payment);
                // update room_booking payment status PAID
                booking.setPaymentStatus("PAID");
                roomBookingMapper.updateById(booking);

                Long bookingId = booking.getId(); // get booking id
                RoomBookingDetailsResponse bookingDetails = roomBookingMapper.getBookingDetailsById(bookingId);

                return Response.success(bookingDetails)
                        .setMessage("Booking confirmed with room grade: " + bookingDetails.getRoomGrade());
            }
        } else if (booking == null) {

            return Response.success()
                    .setMessage("Booking not found" );
        } else {
            return Response.error(500, "Booking has already been paid or expired. " );

        }
        return Response.error(500, "Booking has already been paid or expired. " );
    }

    // 模拟支付成功
    private boolean simulatePayment(PaymentRequest paymentRequest) {
        // 模拟支付成功
        return true;
    }
}