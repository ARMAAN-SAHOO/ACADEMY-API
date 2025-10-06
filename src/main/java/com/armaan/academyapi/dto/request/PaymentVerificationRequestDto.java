package com.armaan.academyapi.dto.request;

import lombok.Data;

@Data
public class PaymentVerificationRequestDto {
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String signature;
}
