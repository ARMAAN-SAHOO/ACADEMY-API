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
    public ExamResult recordResult(ExamResult examResult) {
        return examResultRepository.save(examResult);
    }

    @Override
    public ExamResult getResultById(Long resultId) {
        return examResultRepository.findById(resultId)
                .orElseThrow(() -> new EntityNotFoundException("ExamResult not found"));
    }

    @Override
    public List<ExamResult> getResultsByExam(Long examId) {
        return examResultRepository.findByExamId(examId);
    }

    @Override
    public List<ExamResult> getResultsByStudent(Long studentId) {
        return examResultRepository.findByStudentId(studentId);
    }

    @Override
    public void deleteResult(Long resultId) {
        examResultRepository.deleteById(resultId);
    }

    @Override
    public Result recordResult(Result result) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recordResult'");
    }

    @Override
    public Result getResult(Long resultId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }

    @Override
    public List<Result> getResultsForExam(Long examId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResultsForExam'");
    }

    @Override
    public List<Result> getResultsForStudent(Long studentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResultsForStudent'");
    }
}

