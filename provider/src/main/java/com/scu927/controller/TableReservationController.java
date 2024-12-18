package com.scu927.controller;

import com.scu927.common.Response;
import com.scu927.controller.request.TableReservationRequest;

import com.scu927.service.TableReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chuhan
 * @date 2024/9/8
 */
@RestController
@RequestMapping("/api/table-reservations")
public class TableReservationController {

    @Autowired
    private TableReservationService tableReservationService;

    @PostMapping("/reserve")
    public Response<?> reserveTable(@RequestBody TableReservationRequest request, HttpServletRequest httpServletRequest) {
        return tableReservationService.reserveTable(request, httpServletRequest);
    }
}