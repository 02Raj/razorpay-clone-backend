package com.divyansh.razorpay.merchant.dto.request;

import com.divyansh.razorpay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest(

        @NotNull(message = "Name should be provided")
        @Size(max = 50, message = "Name Should not be more tan 50 characters long")
        String name ,

        @Email
        @NotNull(message = "Email is Requred")
        String email,

        @NotNull(message = "Password is Required")
        @Size(min = 8, message = "Password Should be at least 8 characters long")
        String password,

        @Size(max = 50, message = "Business name should not be more than 50 characters long ")
        String BusinessName,


        BusinessType businessType
) {
}
