package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.ResultRequestDto;
import com.armaan.academyapi.dto.response.ResultResponseDto;

public interface ResultService {
    ResultResponseDto recordResult(ResultRequestDto result);
    ResultResponseDto getResultById(Long resultId);
    List<ResultResponseDto> getAllResults();
    List<ResultResponseDto> getResultsForExam(Long examId);
    List<ResultResponseDto> getResultsForStudent(Long studentId);
}
