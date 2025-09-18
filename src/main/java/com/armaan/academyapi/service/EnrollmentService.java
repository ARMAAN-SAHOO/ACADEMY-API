package com.armaan.academyapi.service;


import java.util.List;
import com.armaan.academyapi.entity.Enrollment;

public interface EnrollmentService {
    Enrollment enrollStudent(Long studentId, Long batchId);
    Enrollment getEnrollment(Long enrollmentId);
    List<Enrollment> getEnrollmentsForStudent(Long studentId);
    List<Enrollment> getEnrollmentsForBatch(Long batchId);
    void cancelEnrollment(Long enrollmentId);
}
