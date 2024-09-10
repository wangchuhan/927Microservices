package com.scu927.service;

import com.scu927.common.Response;
import com.scu927.controller.request.PaymentRequest;


/**
 * @author Chuhan
 * @date 2024/9/9
 */
public interface IPaymentService {

    Response<?> processPayment(String authorizationHeader,PaymentRequest paymentRequest);


}
