package com.armaan.academyapi.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.dto.response.PaymentResponseDto;
import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.entity.Payment;
import com.armaan.academyapi.entity.PaymentStatus;

import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{

    List<Payment> findByEnrollmentStudentStudentId(Long studentId);

    List<Payment> findAllByEnrollmentStudentStudentId(Long studentId);

    List<Payment> findAllByEnrollmentEnrollmentId(Long enrollmentId);

    Iterable<PaymentResponseDto> findByEnrollmentAndStatus(Enrollment enrollment, PaymentStatus created);

    List<Payment> findByStatusAndCreatedAtBefore(PaymentStatus created, LocalDateTime expiryThreshold);

    Optional<Enrollment> findByEnrollmentEnrollmentId(Long enrollmentId);

}
