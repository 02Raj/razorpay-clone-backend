package com.divyansh.razorpay.payment.service.impl;

import com.divyansh.razorpay.common.enums.OrderStatus;
import com.divyansh.razorpay.common.exception.DuplicateResourceException;
import com.divyansh.razorpay.payment.dto.request.createOrderRequest;
import com.divyansh.razorpay.payment.dto.response.OrderResponse;
import com.divyansh.razorpay.payment.entity.OrderRecord;
import com.divyansh.razorpay.payment.repository.OrderRepository;
import com.divyansh.razorpay.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private int dea
    @Override
    public @Nullable OrderResponse create(UUID merchantId,createOrderRequest request) {
   if(request.receipt() != null && orderRepository.existsByMerchantIdAndReceipt(merchantId,request)){
       throw new DuplicateResourceException("ORDER RECEIPT DUPLICATE", "Order with receipt already exist" + request.receipt());
   }

   OrderRecord order = OrderRecord.builder()
           .receipt(request.receipt())
           .amount(request.amount())
           .notes(request.notes())
           .merchantId(merchantId)
           .orderStatus(OrderStatus.CREATED)

           .expiresAt(request.expireAt() != null ? request.expireAt() :
                   LocalDateTime.now().plusMinutes())
           .build();
        return null;
    }
}
