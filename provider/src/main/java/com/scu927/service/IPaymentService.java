package com.scu927.service;

import com.scu927.common.Response;
import com.scu927.controller.request.PaymentRequest;
import jakarta.servlet.http.HttpServletRequest;


/**
 * @author Chuhan
 * @date 2024/9/9
 */
public interface IPaymentService {

    Response<?> processPayment(PaymentRequest paymentRequest, HttpServletRequest httpServletRequest);


}
