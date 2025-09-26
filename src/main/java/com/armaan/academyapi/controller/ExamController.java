package com.armaan.academyapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.dto.request.ExamRequestDto;
import com.armaan.academyapi.dto.response.ExamResponseDto;
import com.armaan.academyapi.service.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamController {
    private final ExamService examService;

    @PostMapping
    public ResponseEntity<ExamResponseDto> create(@RequestBody ExamRequestDto examRequestDto) {
        return ResponseEntity.ok(examService.createExam(examRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(examService.getExamById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExamResponseDto>> getAll() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
