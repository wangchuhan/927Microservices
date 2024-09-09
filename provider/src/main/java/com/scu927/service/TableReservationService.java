package com.scu927.service;

import com.scu927.common.Response;
import com.scu927.controller.request.TableReservationRequest;


/**
 * @author Chuhan
 * @date 2024/9/8
 */
public interface TableReservationService {
    Response<?> reserveTable(TableReservationRequest request);
}