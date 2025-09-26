package com.armaan.academyapi.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.EnrollmentRequestDto;
import com.armaan.academyapi.dto.response.EnrollmentResponseDto;
import com.armaan.academyapi.dto.response.StudentResponseDto;
import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.entity.Student;
import com.armaan.academyapi.mapper.EnrollmentMapper;
import com.armaan.academyapi.mapper.StudentMapper;
import com.armaan.academyapi.repository.BatchRepository;
import com.armaan.academyapi.repository.EnrollmentRepository;
import com.armaan.academyapi.repository.StudentRepository;
import com.armaan.academyapi.service.EnrollmentService;

import jakarta.persistence.EntityNotFoundException;
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

        Batch batch=batchRepository.findById(enrollmentRequestDto.getBatchId()).orElseThrow(()->new EntityNotFoundException());

        Student student=studentRepository.findById(enrollmentRequestDto.getStudentId()).orElseThrow(()->new EntityNotFoundException());

         if (enrollmentRepository.existsByBatchBatchIdAndStudenttudentId(batch.getBatchId(), student.getStudentId())) {
        throw new IllegalArgumentException("Student is already enrolled in this batch");
    }

        Enrollment enrollment=enrollmentMapper.toEntity(enrollmentRequestDto);
        enrollment.setBatch(batch);
        enrollment.setStudent(student);
        enrollment.setEnrolledOn(LocalDate.now());
        Enrollment savedEnrollment= enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponseDto(savedEnrollment);
    }

    @Override
    public EnrollmentResponseDto getEnrollment(Long enrollmentId) {
        Enrollment enrollment=enrollmentRepository.findById(enrollmentId)
        .orElseThrow(()->new RuntimeException("Not FOund Buddy"));
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
        .orElseThrow(()->new RuntimeException("Not FOund Buddy"));

        enrollment.setDeleted(true);
        
    }

}
