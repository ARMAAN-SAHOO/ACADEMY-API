package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.ExamRequestDto;
import com.armaan.academyapi.dto.response.ExamResponseDto;
import com.armaan.academyapi.dto.update.ExamUpdateDto;

public interface ExamService {
    ExamResponseDto createExam(ExamRequestDto examRequestDto);
    ExamResponseDto updateExam(ExamUpdateDto examUpdateDto);
    ExamResponseDto getExamById(Long examId);
    List<ExamResponseDto> getExamsByBatch(Long batchId);
    void deleteExam(Long examId);
    List<ExamResponseDto> getAllExams();
}

