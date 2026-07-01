package com.divyansh.razorpay.payment.service;

import com.divyansh.razorpay.payment.dto.request.createOrderRequest;
import com.divyansh.razorpay.payment.dto.response.OrderResponse;
import org.jspecify.annotations.Nullable;

public interface OrderService {
    @Nullable OrderResponse create(createOrderRequest request);
}
