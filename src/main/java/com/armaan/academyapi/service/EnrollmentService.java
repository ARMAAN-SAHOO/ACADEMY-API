package com.armaan.academyapi.service;


import java.util.List;

import com.armaan.academyapi.dto.request.EnrollmentRequestDto;
import com.armaan.academyapi.dto.response.EnrollmentResponseDto;
import com.armaan.academyapi.dto.response.StudentResponseDto;

public interface EnrollmentService {
    EnrollmentResponseDto enrollStudent(EnrollmentRequestDto enrollmentRequestDto);
    EnrollmentResponseDto getEnrollment(Long enrollmentId);
    List<EnrollmentResponseDto> getEnrollmentsForStudent(Long studentId);
    List<EnrollmentResponseDto> getEnrollmentsForBatch(Long batchId);
    List<StudentResponseDto> getStudentsInBatch(Long batchId);
    void cancelEnrollment(Long enrollmentId);
}
