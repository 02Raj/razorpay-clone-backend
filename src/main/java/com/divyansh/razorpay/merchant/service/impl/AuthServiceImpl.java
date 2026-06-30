package com.divyansh.razorpay.merchant.service.impl;

import com.divyansh.razorpay.common.enums.MerchantStatus;
import com.divyansh.razorpay.common.enums.UserRole;
import com.divyansh.razorpay.common.exception.DuplicateResourceException;
import com.divyansh.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.divyansh.razorpay.merchant.dto.response.MerchantResponse;
import com.divyansh.razorpay.merchant.entity.AppUser;
import com.divyansh.razorpay.merchant.entity.Merchant;
import com.divyansh.razorpay.merchant.repository.AppUserRepository;
import com.divyansh.razorpay.merchant.repository.MerchantRepository;
import com.divyansh.razorpay.merchant.service.AuthService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;

    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {

        if (merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL","Merchant with email already exists: " + request.email());
        }

        Merchant  merchant = Merchant.builder()
                .name(request.name())
                .email(request.email())
                .contactNumber(request.contactNumber())
                .businessName(request.BusinessName())
                .businessType(request.businessType())
                .status(MerchantStatus.PENDING_KYC)
                .build();
        merchant = merchantRepository.save(merchant);

        AppUser appUser = AppUser.builder().
                name(request.name()).
        email(request.email())
                .merchant(merchant)
                .passwordHash(request.password()) // TODO: encrypt using bcrypt
                .role(UserRole.OWNER)
        .build();

        appUserRepository.save(appUser);
        return new MerchantResponse(merchant.getId(),merchant.getName(),merchant.getEmail(),merchant.getBusinessName(),merchant.getBusinessType(),merchant.getStatus());
    }


}
