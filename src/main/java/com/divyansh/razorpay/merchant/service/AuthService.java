package com.divyansh.razorpay.merchant.service;

import com.divyansh.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.divyansh.razorpay.merchant.dto.response.MerchantResponse;
import jakarta.validation.Valid;

public interface AuthService {

    MerchantResponse signup(@Valid MerchantSignupRequest request);
}
