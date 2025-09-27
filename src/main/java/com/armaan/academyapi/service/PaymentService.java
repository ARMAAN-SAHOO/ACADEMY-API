package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.PaymentRequestDto;
import com.armaan.academyapi.dto.response.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto recordPayment(PaymentRequestDto dto);
    List<PaymentResponseDto> getPaymentsForStudent(Long studentId);
    PaymentResponseDto getPaymentById(Long paymentId);
    List<PaymentResponseDto> getPaymentsForEnrollment(Long enrollmentId);
    List<PaymentResponseDto> getAllPayments();
}
