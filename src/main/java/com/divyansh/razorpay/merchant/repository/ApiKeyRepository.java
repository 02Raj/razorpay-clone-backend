package com.divyansh.razorpay.merchant.repository;

import com.divyansh.razorpay.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {


}
