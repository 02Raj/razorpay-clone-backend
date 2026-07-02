package com.divyansh.razorpay.merchant.service.impl;

import com.divyansh.razorpay.common.exception.ResourceNotFoundException;
import com.divyansh.razorpay.common.util.RandomizerUtil;
import com.divyansh.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.divyansh.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.divyansh.razorpay.merchant.dto.response.ApiKeyResponse;
import com.divyansh.razorpay.merchant.entity.ApiKey;
import com.divyansh.razorpay.merchant.entity.Merchant;
import com.divyansh.razorpay.merchant.repository.ApiKeyRepository;
import com.divyansh.razorpay.merchant.repository.MerchantRepository;
import com.divyansh.razorpay.merchant.service.ApiKeyService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String keyId = "rzp_" + request.environment().name().toUpperCase() + RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40); // TODO : replace with cryptograph random hex
//     a-z, A-z,0-9,-,_
//      a-z ,0-9

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret)
                .environment(request.environment())
                .enabled(true)
                .build();
        
        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId().toString(), apiKey.getKeyId(), rawSecret, request.environment());
    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyRepository.findByMerchant_Id(merchantId)
                .stream()
                .map(apiKey -> new ApiKeyResponse(
                        apiKey.getId(),
                        apiKey.getKeyId(),
                        apiKey.getEnvironment(),
                        apiKey.isEnabled(),
                        apiKey.getLastUsedAt(),null
                ))
                .toList();
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key =  apiKeyRepository.findById(keyId)
                .filter( k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("Api Key", keyId));
        key.setEnabled(false);
    }

    @Override
    @Transactional
    public @Nullable ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey =  apiKeyRepository.findById(keyId)
                .filter( k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("Api Key", keyId));

        if(!apiKey.isEnabled()) throw new RuntimeException("Cannot rotate a disabled key");

        String newRawSecret = RandomizerUtil.randomBase64(40);
        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret);
        apiKey.setRotatedAt(LocalTime.from(LocalDateTime.now()));
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));
        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId().toString(), apiKey.getKeyId(), newRawSecret, apiKey.getEnvironment());

    }


}
