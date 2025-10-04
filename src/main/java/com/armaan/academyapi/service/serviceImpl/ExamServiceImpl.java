package com.armaan.academyapi.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.ExamRequestDto;
import com.armaan.academyapi.dto.response.ExamResponseDto;
import com.armaan.academyapi.dto.update.ExamUpdateDto;
import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.entity.Course;
import com.armaan.academyapi.entity.Exam;
import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.exception.BusinessException;
import com.armaan.academyapi.exception.ResourceNotFoundException;
import com.armaan.academyapi.mapper.ExamMapper;
import com.armaan.academyapi.repository.BatchRepository;
import com.armaan.academyapi.repository.CourseRepository;
import com.armaan.academyapi.repository.ExamRepository;
import com.armaan.academyapi.service.ExamService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final CourseRepository courseRepository;
    private final BatchRepository batchRepository;
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    @Override
    @Transactional
    public ExamResponseDto createExam(ExamRequestDto examRequestDto) {

        Batch batch=batchRepository.findById(examRequestDto.getBatchId())
        .orElseThrow(()->new ResourceNotFoundException("Batch",examRequestDto.getBatchId()));

        Course course=courseRepository.findById(examRequestDto.getCourseId())
        .orElseThrow(()->new ResourceNotFoundException("Course",examRequestDto.getCourseId()));

        LocalDate today=LocalDate.now();
        LocalTime now=LocalTime.now();

        if(examRequestDto.getDate().isBefore(today)){
                    throw new BusinessException("Exam date cannot be in the past");
        }
        
        if(examRequestDto.getDate().isEqual(today) && examRequestDto.getStartTime().isBefore(now)){
                    throw new BusinessException("Exam time cannot be in the past");
        }

            if (examRequestDto.getEndTime().isBefore(examRequestDto.getStartTime())) {
        throw new BusinessException("End time cannot be before start time");
    }

           boolean conflict = examRepository.existsByBatchAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
    batch,
    examRequestDto.getDate(),
    examRequestDto.getEndTime(),
    examRequestDto.getStartTime()
);

if (conflict) {
    throw new BusinessException("Time slot overlaps with an existing exam for this batch on this date");
}


        Exam exam=examMapper.toEntity(examRequestDto);
        exam.setBatch(batch);
        exam.setCourse(course);
        exam.setStatus(ExamStatus.SCHEDULED);
        Exam savedExam= examRepository.save(exam);
        return examMapper.toResponseDto(savedExam);
    }

    @Override
    public ExamResponseDto getExamById(Long examId) {
        Exam exam= examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam",examId));
            return examMapper.toResponseDto(exam);
    }

    @Override
    public List<ExamResponseDto> getExamsByBatch(Long batchId) {
        return examRepository.findAllByBatchBatchId(batchId).stream().map(examMapper::toResponseDto).toList();
    }

    @Override
    @Transactional
    public void deleteExam(Long examId) {
        Exam exam =examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam",examId));
        exam.setStatus(ExamStatus.CANCELLED);
    }

    @Override
    public List<ExamResponseDto> getAllExams() {
        return examRepository.findAll().stream().map(examMapper::toResponseDto).toList();
    }

    @Override
    public ExamResponseDto updateExam(ExamUpdateDto examUpdateDto) {
        Exam exam =examRepository.findById(examUpdateDto.getExamId())
                .orElseThrow(() -> new ResourceNotFoundException("Exam",examUpdateDto.getExamId()));

        examMapper.update(examUpdateDto, exam);
        return examMapper.toResponseDto(exam);
    }
}
