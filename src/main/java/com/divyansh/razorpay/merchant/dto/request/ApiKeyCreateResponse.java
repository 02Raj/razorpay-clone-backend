package com.divyansh.razorpay.merchant.dto.request;

public record ApiKeyCreateResponse (

        String id,
        String keyId,
        String keySecret,
        String environment
){
}
