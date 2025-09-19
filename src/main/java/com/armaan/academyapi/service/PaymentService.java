package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.Payment;

public interface PaymentService {
    Payment recordPayment(Payment payment);
    Payment getPaymentById(Long paymentId);
    List<Payment> getAllPayments();
    List<Payment> getPaymentsForEnrollment(Long enrollmentId);
    List<Payment> getPaymentsForStudent(Long studentId);
}

