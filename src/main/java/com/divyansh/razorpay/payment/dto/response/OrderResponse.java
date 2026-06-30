package com.divyansh.razorpay.payment.dto.response;

import com.divyansh.razorpay.common.entity.Money;
import com.divyansh.razorpay.common.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(

        UUID id,
        UUID merchantId,
        String receipt,
        Money amount,
        OrderStatus status,
        Integer attempts,
        LocalDateTime expireAt,
        LocalDateTime createdAt
) {
}
