package com.scu927.client;

import com.scu927.common.Response;
import com.scu927.controller.request.TableReservationRequest;
import com.scu927.controller.response.TestClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Chuhan
 * @date 2024/9/7
 */

// FeignClient 注解指定了服务名
@FeignClient(name = "service-provider")
public interface ProviderClient {

    @PostMapping("/api/table-reservations/reserve")
    Response<?> reserveTable(@RequestBody TableReservationRequest request);




}