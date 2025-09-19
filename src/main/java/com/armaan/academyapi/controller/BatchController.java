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

import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.service.BatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/batches")
public class BatchController {
    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<Batch> create(@RequestBody Batch batch) {
        return ResponseEntity.ok(batchService.createBatch(batch));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Batch> get(@PathVariable Long id) {
        return ResponseEntity.ok(batchService.getBatchById(id));
    }

    @GetMapping
    public ResponseEntity<List<Batch>> getAll() {
        return ResponseEntity.ok(batchService.getAllBatches());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return ResponseEntity.noContent().build();
    }
}