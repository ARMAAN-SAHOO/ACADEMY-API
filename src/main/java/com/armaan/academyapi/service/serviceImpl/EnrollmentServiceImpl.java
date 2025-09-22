package com.armaan.academyapi.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.entity.Student;
import com.armaan.academyapi.repository.EnrollmentRepository;
import com.armaan.academyapi.service.EnrollmentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment enrollStudent(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment getEnrollment(Long enrollmentId) {
        Enrollment enrollment=enrollmentRepository.findById(enrollmentId)
        .orElseThrow(()->new RuntimeException("Not FOund Buddy"));
        return enrollment;
    }

    @Override
    public List<Enrollment> getEnrollmentsForStudent(Long studentId) {
        return enrollmentRepository.findAllByStudentStudentId(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsForBatch(Long batchId) {
        return enrollmentRepository.findAllByBatchBatchId(batchId);
    }

    @Override
    public List<Student> getStudentsInBatch(Long batchId) {
            List<Enrollment> enrollments=enrollmentRepository.findAllByBatchBatchId(batchId);
            return enrollments.stream().map(Enrollment::getStudent).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelEnrollment(Long enrollmentId) {

        Enrollment enrollment=enrollmentRepository.findById(enrollmentId)
        .orElseThrow(()->new RuntimeException("Not FOund Buddy"));

        enrollment.setDeleted(true);
        
    }

}
