package com.divyansh.razorpay.merchant.service;


import com.divyansh.razorpay.common.exception.ResourceNotFoundException;
import com.divyansh.razorpay.merchant.dto.request.ApiKeyCreateResponse;
import com.divyansh.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.divyansh.razorpay.merchant.entity.Merchant;
import com.divyansh.razorpay.merchant.repository.MerchantRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface ApiKeyService {

    private final MerchantRepository merchantRepository

   @Override
   public  ApiKeyCreateResponse create(UUID merchnatId, CreateApiKeyRequest request){
       Merchant merchant = merchantRepository.findById(merchnatId).orElseThrow(() -> new ResourceNotFoundException("merchant",merchnatId));

       String keyId = "rzp_"+ request.environment().name().toUpperCae() + "big"
       String rawSecret = "big_random_seceret": // TODO : replace with cryptograph random hex
//     a-z, A-z,0-9,-,_
//      a-z ,0-9
        return null;


    }
}
