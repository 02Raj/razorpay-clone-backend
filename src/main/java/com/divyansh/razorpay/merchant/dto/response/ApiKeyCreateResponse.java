package com.divyansh.razorpay.merchant.dto.response;

import com.divyansh.razorpay.merchant.entity.Environment;

public record ApiKeyCreateResponse (

        String id,
        String keyId,
        String keySecret,
        Environment environment
){
}
