package com.divyansh.razorpay.merchant.service;

import com.divyansh.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.divyansh.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.divyansh.razorpay.merchant.dto.response.ApiKeyResponse;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);

    List<ApiKeyResponse> listByMerchant(UUID merchantId);

    void revoke(UUID merchantId, UUID keyId);
}
