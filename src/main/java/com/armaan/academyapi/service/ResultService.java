package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.Result;

public interface ResultService {
    Result recordResult(Result result);
    Result getResult(Long resultId);
    List<Result> getResultsForExam(Long examId);
    List<Result> getResultsForStudent(Long studentId);
}
