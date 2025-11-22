package com.armaan.academyapi.dto.request;


import com.armaan.academyapi.enums.PaymentMode;
import com.armaan.academyapi.enums.PaymentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDto {

    private String razorpayOrderId;
    private String razorpayPaymentId;

    @NotNull
    @Positive
    private Double amount;

    @NotBlank
    private String currency = "INR";

    @NotNull
    private Long enrollmentId;

    @NotNull
    private PaymentStatus status;

    @NotNull
    private PaymentMode paymentMode; 
}
