package com.armaan.academyapi.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.PaymentRequestDto;
import com.armaan.academyapi.dto.request.PaymentVerificationRequestDto;
import com.armaan.academyapi.dto.response.OrderResponseDto;
import com.armaan.academyapi.dto.response.PaymentResponseDto;
import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.entity.EnrollmentStatus;
import com.armaan.academyapi.entity.Payment;
import com.armaan.academyapi.entity.PaymentStatus;
import com.armaan.academyapi.exception.BusinessException;
import com.armaan.academyapi.exception.ResourceNotFoundException;
import com.armaan.academyapi.mapper.EnrollmentMapper;
import com.armaan.academyapi.mapper.PaymentMapper;
import com.armaan.academyapi.repository.EnrollmentRepository;
import com.armaan.academyapi.repository.PaymentRepository;
import com.armaan.academyapi.service.PaymentGatewayService;
import com.armaan.academyapi.service.PaymentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentGatewayService paymentGatewayService;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId; // for frontend

    /**
     * Create Razorpay order only when user clicks "Pay Now"
     */
   
    @Override
    @Transactional
    public OrderResponseDto initiatePayment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", enrollmentId));

        if (enrollment.getStatus() == EnrollmentStatus.CANCELLED) {
            throw new BusinessException("Enrollment is cancelled, cannot pay.");
        }

        // expire any existing CREATED payments
        paymentRepository.findByEnrollmentAndStatus(enrollment, PaymentStatus.CREATED)
                .forEach(p -> p.setStatus(PaymentStatus.CANCELLED));
        paymentRepository.flush();

        int amount = enrollment.getBatch().getFee();
        String receiptId = "enroll_" + enrollmentId + "_" + System.currentTimeMillis();

        OrderResponseDto order = paymentGatewayService.createOrder(amount, receiptId);

        Payment payment = Payment.builder()
                .enrollment(enrollment)
                .razorpayOrderId(order.getId())
                .amount((double) amount)
                .currency("INR")
                .status(PaymentStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);
        return order;
    }

    /**
     * Called after Razorpay payment success
     */
  @Override
@Transactional
public PaymentResponseDto confirmPayment(PaymentVerificationRequestDto dto) {
    Payment payment = paymentRepository.findByRazorpayOrderId(dto.getRazorpayOrderId())
            .orElseThrow(() -> new ResourceNotFoundException("Payment", dto.getRazorpayOrderId()));

    boolean valid = paymentGatewayService.verifySignature(dto.getRazorpayOrderId(),
            dto.getRazorpayPaymentId(), dto.getSignature());

    if (!valid) {
        payment.setStatus(PaymentStatus.FAILED);
        payment.setUpdatedAt(LocalDateTime.now());
        paymentRepository.save(payment);
        throw new BusinessException("Invalid payment signature.");
    }

    if (payment.getStatus() == PaymentStatus.CAPTURED) {
        return paymentMapper.toResponseDto(payment); // already processed
    }

    payment.setRazorpayPaymentId(dto.getRazorpayPaymentId());
    payment.setStatus(PaymentStatus.CAPTURED);
    payment.setUpdatedAt(LocalDateTime.now());
    paymentRepository.save(payment);

    // mark enrollment as PAID
    Enrollment enrollment = payment.getEnrollment();
    enrollment.setStatus(EnrollmentStatus.PAID);
    enrollmentRepository.save(enrollment);

    // expire any other pending payments
    paymentRepository.findByEnrollmentAndStatus(enrollment, PaymentStatus.CREATED)
            .forEach(p -> {
                p.setStatus(PaymentStatus.CANCELLED);
                p.setUpdatedAt(LocalDateTime.now());
            });
    paymentRepository.flush();

    return paymentMapper.toResponseDto(payment);
}


    /**
     * Get all payments for an enrollment
     */
    @Override
    public List<PaymentResponseDto> getPaymentsForEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", enrollmentId));

        return paymentRepository.findByEnrollmentEnrollmentId(enrollmentId)
                .stream()
                .map(paymentMapper::toResponseDto)
                .toList();
    }

    /**
     * Optional: scheduled cleanup for stale payments
     */
    @Scheduled(cron = "0 0 0 * * *") // every midnight
    public void cancelOldPayments() {
        LocalDateTime expiryThreshold = LocalDateTime.now().minusDays(5);
        List<Payment> oldPayments = paymentRepository.findByStatusAndCreatedAtBefore(PaymentStatus.CREATED, expiryThreshold);
        oldPayments.forEach(p -> p.setStatus(PaymentStatus.CANCELLED));
        paymentRepository.saveAll(oldPayments);
    }

    @Override
    public List<PaymentResponseDto> getPaymentsForStudent(Long studentId) {
        return paymentRepository.findAllByEnrollmentStudentStudentId(studentId).stream().map(paymentMapper::toResponseDto).toList();
    }

    @Override
    public PaymentResponseDto getPaymentById(Long paymentId) {
        Payment payment= paymentRepository.findById(paymentId)
                .orElseThrow(()-> new ResourceNotFoundException("Payment",paymentId));
        return paymentMapper.toResponseDto(payment);
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        return paymentRepository.findAll().stream().map(paymentMapper::toResponseDto).toList();
    }
}

