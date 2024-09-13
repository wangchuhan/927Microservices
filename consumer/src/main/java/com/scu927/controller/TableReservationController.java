package com.scu927.controller;


import com.scu927.common.Response;
import com.scu927.controller.request.TableReservationRequest;
import com.scu927.service.ITableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
@RestController
@RequestMapping("/api/table-reservations")
public class TableReservationController {

    @Autowired
    private ITableReservationService tableReservationService;

    @PostMapping("/reserve")
    public Response<?> reserveTable(
            @RequestBody TableReservationRequest request) {


        return tableReservationService.reserveTable(request);
    }
}