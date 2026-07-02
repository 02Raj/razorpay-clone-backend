package com.divyansh.razorpay.payment.controller;

import com.divyansh.razorpay.payment.dto.request.createOrderRequest;
import com.divyansh.razorpay.payment.dto.response.OrderResponse;
import com.divyansh.razorpay.payment.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    UUID merchantId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000") ; // TODO : replace  iwith merchantContext

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody  @Valid createOrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(merchantId, request));
    }
}
