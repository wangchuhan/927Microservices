package com.scu927.service.impl;

import com.scu927.client.ProviderClient;
import com.scu927.common.Response;
import com.scu927.config.JwtUtil;
import com.scu927.controller.request.PaymentRequest;
import com.scu927.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Chuhan
 * @date 2024/9/9
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private ProviderClient providerClient;


    @Override
    public Response<?> processPayment(PaymentRequest request) {

        return providerClient.payment(request);
    }


}