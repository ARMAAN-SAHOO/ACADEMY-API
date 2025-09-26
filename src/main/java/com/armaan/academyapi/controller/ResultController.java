package com.armaan.academyapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.dto.request.ResultRequestDto;
import com.armaan.academyapi.dto.response.ResultResponseDto;
import com.armaan.academyapi.service.ResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
public class ResultController {
    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<ResultResponseDto> create(@RequestBody ResultRequestDto resultRequestDto) {
        return ResponseEntity.ok(resultService.recordResult(resultRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.getResultById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResultResponseDto>> getAll() {
        return ResponseEntity.ok(resultService.getAllResults());
    }
} 
