package com.armaan.academyapi.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.EnrollmentRequestDto;
import com.armaan.academyapi.dto.response.EnrollmentResponseDto;
import com.armaan.academyapi.dto.response.StudentResponseDto;
import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.entity.EnrollmentStatus;
import com.armaan.academyapi.entity.Student;
import com.armaan.academyapi.exception.BusinessException;
import com.armaan.academyapi.exception.ResourceNotFoundException;
import com.armaan.academyapi.mapper.EnrollmentMapper;
import com.armaan.academyapi.mapper.StudentMapper;
import com.armaan.academyapi.repository.BatchRepository;
import com.armaan.academyapi.repository.EnrollmentRepository;
import com.armaan.academyapi.repository.StudentRepository;
import com.armaan.academyapi.service.EnrollmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final BatchRepository batchRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public EnrollmentResponseDto enrollStudent(EnrollmentRequestDto enrollmentRequestDto) {

        Batch batch=batchRepository.findById(enrollmentRequestDto.getBatchId())
        .orElseThrow(()->new ResourceNotFoundException("Batch", enrollmentRequestDto.getBatchId()));

        Student student=studentRepository.findById(enrollmentRequestDto.getStudentId())
        .orElseThrow(()->new ResourceNotFoundException("Course", enrollmentRequestDto.getStudentId()));

         if (enrollmentRepository.existsByBatchBatchIdAndStudentStudentId(batch.getBatchId(), student.getStudentId())) {
        throw new BusinessException("Student is already enrolled in this batch");
    }

        Enrollment enrollment=enrollmentMapper.toEntity(enrollmentRequestDto);
        enrollment.setBatch(batch);
        enrollment.setStudent(student);
        enrollment.setEnrolledOn(LocalDate.now());
        enrollment.setStatus(EnrollmentStatus.ENROLLED);
        enrollment.setPaymetDue(LocalDate.now().plusDays(5));
        Enrollment savedEnrollment= enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponseDto(savedEnrollment);
    }

    @Override
    public EnrollmentResponseDto getEnrollment(Long enrollmentId) {
        Enrollment enrollment=enrollmentRepository.findById(enrollmentId)
        .orElseThrow(()->new ResourceNotFoundException("Enrollment",enrollmentId));
        return enrollmentMapper.toResponseDto(enrollment);
    }

    @Override
    public List<EnrollmentResponseDto> getEnrollmentsForStudent(Long studentId) {
        return enrollmentRepository.findAllByStudentStudentId(studentId).stream().map(enrollement->enrollmentMapper.toResponseDto(enrollement)).toList();
    }

    @Override
    public List<EnrollmentResponseDto> getEnrollmentsForBatch(Long batchId) {
        return enrollmentRepository.findAllByBatchBatchId(batchId).stream().map(enrollment->enrollmentMapper.toResponseDto(enrollment)).toList();
    }

    @Override
    public List<StudentResponseDto> getStudentsInBatch(Long batchId) {
            List<Enrollment> enrollments=enrollmentRepository.findAllByBatchBatchId(batchId);
            return enrollments.stream().map(Enrollment::getStudent).map(studentMapper::toResponseDto).toList();
    }

    @Override
    @Transactional
    public void cancelEnrollment(Long enrollmentId) {

        Enrollment enrollment=enrollmentRepository.findById(enrollmentId)
        .orElseThrow(()->new ResourceNotFoundException("Enrollment",enrollmentId));

        enrollment.setDeleted(true);
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        
    }

}
