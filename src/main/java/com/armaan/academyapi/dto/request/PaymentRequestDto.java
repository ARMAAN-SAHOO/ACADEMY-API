package com.armaan.academyapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDto {
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String paymentMode;   // UPI, Card, NetBanking, etc.
    private Double amount;
    private String currency;      // INR
    private Long enrollmentId;    // link to student enrollment
    //private Long batchId;         // optional, if paying for a batch
    private String status;        // SUCCESS, FAILED, PENDING
}
