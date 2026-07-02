package com.divyansh.razorpay.payment.dto.response;

import com.divyansh.razorpay.common.enums.PaymentMethd;
import com.divyansh.razorpay.common.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse(
        UUID id,
        UUID orderId,
        UUID amount,
        PaymentStatus status,
        PaymentMethd method,
        Map<String,Object> methodDetails,
        String cardLastFour,
        String cardBrand,
        String bankReference,
        String errorCode,
        String refundedAmountPaise,
        LocalDateTime capturedAt,
        LocalDateTime createdAt
) {

}
