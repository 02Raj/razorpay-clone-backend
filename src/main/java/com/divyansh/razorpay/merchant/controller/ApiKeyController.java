package com.divyansh.razorpay.merchant.controller;

import com.divyansh.razorpay.merchant.dto.request.ApiKeyCreateResponse;
import com.divyansh.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.divyansh.razorpay.merchant.service.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/merchants/{merchnatId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

    private  final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchnatId, @Valid @ResponseBody CreateApiKeyRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(
                apiKeyService.create(merchnatId,request)
        );
    }


}
