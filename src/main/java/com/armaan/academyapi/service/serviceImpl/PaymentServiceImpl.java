package com.armaan.academyapi.service.serviceImpl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.PaymentRequestDto;
import com.armaan.academyapi.dto.response.PaymentResponseDto;
import com.armaan.academyapi.entity.Payment;
import com.armaan.academyapi.mapper.PaymentMapper;
import com.armaan.academyapi.repository.PaymentRepository;
import com.armaan.academyapi.service.PaymentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDto recordPayment(PaymentRequestDto paymentRequestDto) {
        Payment payment=paymentMapper.toEntity(paymentRequestDto);
        Payment savedPayment= paymentRepository.save(payment);
        return paymentMapper.toResponseDto(savedPayment);
    }

    @Override
    public List<PaymentResponseDto> getPaymentsForStudent(Long studentId) {
        return paymentRepository.findAllByEnrollmentStudentStudentId(studentId).stream().map(paymentMapper::toResponseDto).toList();
    }

    @Override
    public PaymentResponseDto getPaymentById(Long paymentId) {
        Payment payment= paymentRepository.findById(paymentId)
                .orElseThrow(()-> new RuntimeException("Payment not found"));
        return paymentMapper.toResponseDto(payment);
    }

    @Override
    public List<PaymentResponseDto> getPaymentsForEnrollment(Long enrollmentId) {
        return paymentRepository.findAllByEnrollmentEnrollmentId(enrollmentId).stream().map(paymentMapper::toResponseDto).toList();
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        return paymentRepository.findAll().stream().map(paymentMapper::toResponseDto).toList();
    }
}

