package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.Exam;

public interface ExamService {
    Exam createExam(Exam exam);
    Exam getExamById(Long examId);
    List<Exam> getExamsByBatch(Long batchId);
    void deleteExam(Long examId);
}

