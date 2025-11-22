package com.armaan.academyapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.armaan.academyapi.enums.PaymentMode;
import com.armaan.academyapi.enums.PaymentStatus;

@Getter
@Setter
public class PaymentResponseDto {
    private Long paymentId;
    private Double amount;
    private String currency;
    private PaymentStatus status;
    private PaymentMode paymentMode;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private Long enrollmentId;
    private Long batchId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
