package com.divyansh.razorpay.payment.service;

import com.divyansh.razorpay.payment.dto.request.createOrderRequest;
import com.divyansh.razorpay.payment.dto.response.OrderResponse;
import com.divyansh.razorpay.payment.dto.response.PaymentResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    @Nullable OrderResponse create(UUID merchantId, createOrderRequest request);

    OrderResponse getById(UUID merchantId, UUID orderId);

    OrderResponse cancel(UUID merchantId, UUID orderId);

    List<PaymentResponse> listPayments(UUID merchaantId, UUID orderId);
}
