package com.scu927.service.impl;

import com.scu927.client.ProviderClient;
import com.scu927.common.Response;
import com.scu927.controller.request.TableReservationRequest;
import com.scu927.service.ITableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chuhan
 * @date 2024/9/7
 */


@Service
public class TableReservationImpl implements ITableReservationService {

    @Autowired
    private ProviderClient providerClient;


    @Override
    public Response<?> reserveTable(TableReservationRequest request) {
        return providerClient.reserveTable(request);
    }


}