package com.armaan.academyapi.service;


import java.util.List;
import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.entity.Student;

public interface EnrollmentService {
    Enrollment enrollStudent(Enrollment enrollment);
    Enrollment getEnrollment(Long enrollmentId);
    List<Enrollment> getEnrollmentsForStudent(Long studentId);
    List<Enrollment> getEnrollmentsForBatch(Long batchId);
    List<Student> getStudentsInBatch(Long batchId);
    void cancelEnrollment(Long enrollmentId);
}
