package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Exam;
import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.repository.ExamRepository;
import com.armaan.academyapi.service.ExamService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam getExamById(Long examId) {
        return examRepository.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found"));
    }

    @Override
    public List<Exam> getExamsByBatch(Long batchId) {
        return examRepository.findAllByBatchBatchId(batchId);
    }

    @Override
    public void deleteExam(Long examId) {
        Exam exam =examRepository.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found"));
        exam.setStatus(ExamStatus.CANCELLED);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }
}
