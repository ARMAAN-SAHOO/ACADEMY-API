package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.ResultRequestDto;
import com.armaan.academyapi.dto.response.ResultResponseDto;
import com.armaan.academyapi.entity.Exam;
import com.armaan.academyapi.entity.Result;
import com.armaan.academyapi.entity.Student;
import com.armaan.academyapi.mapper.ResultMapper;
import com.armaan.academyapi.repository.ExamRepository;
import com.armaan.academyapi.repository.ResultRepository;
import com.armaan.academyapi.repository.StudentRepository;
import com.armaan.academyapi.service.ResultService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository examResultRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final ResultMapper resultMapper;

    @Override
    public ResultResponseDto recordResult(ResultRequestDto resultRequestDto) {

        Student student=studentRepository.findById(resultRequestDto.getStudentId()).orElseThrow(()->new EntityNotFoundException());
        Exam exam=examRepository.findById(resultRequestDto.getExamId()).orElseThrow(()->new EntityNotFoundException());

        Result result=resultMapper.toEntity(resultRequestDto);
        result.setStudent(student);
        result.setExam(exam);

        return resultMapper.toResponseDto(result);


    }

    @Override
    public ResultResponseDto getResultById(Long resultId) {
        Result result= examResultRepository.findById(resultId)
                .orElseThrow(() -> new EntityNotFoundException("ExamResult not found"));
        
        return resultMapper.toResponseDto(result);
    }

    @Override
    public List<ResultResponseDto> getResultsForExam(Long examId) {
        return examResultRepository.findByExamExamId(examId).stream().map(resultMapper::toResponseDto).toList();
    }

    @Override
    public List<ResultResponseDto> getResultsForStudent(Long studentId) {
        return examResultRepository.findByStudentStudentId(studentId).stream().map(resultMapper::toResponseDto).toList();
    }

    @Override
    public List<ResultResponseDto> getAllResults() {
        return examResultRepository.findAll().stream().map(resultMapper::toResponseDto).toList();
    }
}

