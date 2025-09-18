package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.Payment;

public interface PaymentService {
    Payment recordPayment(Payment payment);
    Payment getPayment(Long paymentId);
    List<Payment> getPaymentsForEnrollment(Long enrollmentId);
    List<Payment> getPaymentsForStudent(Long studentId);
}

