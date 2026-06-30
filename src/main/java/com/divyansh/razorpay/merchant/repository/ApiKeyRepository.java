package com.divyansh.razorpay.merchant.repository;

import com.divyansh.razorpay.merchant.dto.response.ApiKeyResponse;
import com.divyansh.razorpay.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {


    List<ApiKey> findByMerchant_Id(UUID merchantId);
}
