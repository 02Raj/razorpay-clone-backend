package com.divyansh.razorpay.payment.dto.request;

import com.divyansh.razorpay.common.entity.Money;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Locale;
import java.util.Map;

public record createOrderRequest(

        @NotNull(message = "Amount is required")
        Money amount,

        @Size(max = 100)
        String receipt, //order-id known to merchant
        Map<String,Object> notes,
        Locale expireAt

) {
}
