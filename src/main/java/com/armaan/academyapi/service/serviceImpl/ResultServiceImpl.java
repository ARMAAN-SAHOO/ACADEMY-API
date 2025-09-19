package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Result;
import com.armaan.academyapi.repository.ResultRepository;
import com.armaan.academyapi.service.ResultService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository examResultRepository;

    @Override
    public Result recordResult(Result examResult) {
        return examResultRepository.save(examResult);
    }

    @Override
    public Result getResultById(Long resultId) {
        return examResultRepository.findById(resultId)
                .orElseThrow(() -> new EntityNotFoundException("ExamResult not found"));
    }

    @Override
    public List<Result> getResultsForExam(Long examId) {
        return examResultRepository.findByExamId(examId);
    }

    @Override
    public List<Result> getResultsForStudent(Long studentId) {
        return examResultRepository.findByStudentId(studentId);
    }

    @Override
    public List<Result> getAllResults() {
        return examResultRepository.findAll();
    }
}

