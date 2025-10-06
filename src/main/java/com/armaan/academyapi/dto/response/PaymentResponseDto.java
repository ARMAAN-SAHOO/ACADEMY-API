package com.armaan.academyapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.armaan.academyapi.entity.PaymentStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDto {
    private Long paymentId;
    private Double amount;
    private String currency;
    private PaymentStatus status;
    private String paymentMode;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private Long enrollmentId;
    private Long batchId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
