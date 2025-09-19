package com.armaan.academyapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.entity.Result;
import com.armaan.academyapi.service.ResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/results")
public class ResultController {
    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<Result> create(@RequestBody Result result) {
        return ResponseEntity.ok(resultService.recordResult(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> get(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.getResultById(id));
    }

    @GetMapping
    public ResponseEntity<List<Result>> getAll() {
        return ResponseEntity.ok(resultService.getAllResults());
    }
} 
