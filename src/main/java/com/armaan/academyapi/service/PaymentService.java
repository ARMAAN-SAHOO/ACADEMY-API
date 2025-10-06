package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.PaymentRequestDto;
import com.armaan.academyapi.dto.response.OrderResponseDto;
import com.armaan.academyapi.dto.response.PaymentResponseDto;

public interface PaymentService {
    public OrderResponseDto initiatePayment(Long enrollmentId);
    public PaymentResponseDto confirmPayment(PaymentVerificationRequestDto dto);
    List<PaymentResponseDto> getPaymentsForStudent(Long studentId);
    PaymentResponseDto getPaymentById(Long paymentId);
    List<PaymentResponseDto> getPaymentsForEnrollment(Long enrollmentId);
    List<PaymentResponseDto> getAllPayments();
}
