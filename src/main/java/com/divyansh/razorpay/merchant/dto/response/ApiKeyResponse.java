package com.divyansh.razorpay.merchant.dto.response;

import com.divyansh.razorpay.merchant.entity.Environment;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApiKeyResponse(

        UUID id,
        String keyId,
        Environment environment,
        boolean enabled,
        java.time.LocalTime lastUsedAt,
        LocalDateTime createdAt

) {
}
