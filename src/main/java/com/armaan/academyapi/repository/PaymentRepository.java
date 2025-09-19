package com.armaan.academyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Payment;

import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{

    List<Payment> findByEnrollmentStudentStudentId(Long studentId);

    List<Payment> findAllByEnrollmentStudentStudentId(Long studentId);

    List<Payment> findAllByEnrollmentEnrollmentId(Long enrollmentId);

}
