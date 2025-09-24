package com.armaan.academyapi.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

    private Long paymentId;
    private Long enrollmentId;   // Link payment to a student enrollment
    private Double amount;
    private LocalDate paymentDate;
    private String status;       // PAID or PENDING
    private String paymentMode;  // e.g., CASH, CARD, ONLINE
}