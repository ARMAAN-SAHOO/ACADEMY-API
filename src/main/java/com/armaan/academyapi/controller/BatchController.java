package com.armaan.academyapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.dto.request.BatchRequestDto;
import com.armaan.academyapi.dto.response.BatchResponseDto;
import com.armaan.academyapi.dto.update.BatchUpdateDto;
import com.armaan.academyapi.service.BatchService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<BatchResponseDto> createBatch(@Valid @RequestBody BatchRequestDto dto) {
        BatchResponseDto created = batchService.createBatch(dto);
        return ResponseEntity
                .created(URI.create("/api/batches/" + created.getBatchId()))
                .body(created);
    }
@PatchMapping("/{id}")
public ResponseEntity<BatchResponseDto> updateBatch(
    @PathVariable Long id, @Valid @RequestBody BatchUpdateDto batchUpdateDto) {
    BatchResponseDto updatedBatch = batchService.updateBatch(id, batchUpdateDto);
    return ResponseEntity.ok(updatedBatch);
}


    @GetMapping("/{id}")
    public ResponseEntity<BatchResponseDto> getBatch(@PathVariable Long id) {
        return ResponseEntity.ok(batchService.getBatchById(id));
    }

    @GetMapping
    public ResponseEntity<List<BatchResponseDto>> getAllBatches() {
        return ResponseEntity.ok(batchService.getAllBatches());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return ResponseEntity.noContent().build();
    }
}
