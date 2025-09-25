package com.armaan.academyapi.service;
import java.util.List;

import com.armaan.academyapi.dto.request.BatchRequestDto;
import com.armaan.academyapi.dto.response.BatchResponseDto;

public interface BatchService {
    BatchResponseDto createBatch(BatchRequestDto batchRequestDto);
    BatchResponseDto getBatchById(Long batchId);
    List<BatchResponseDto> getAllBatches();
    BatchResponseDto updateBatch(Long batchId, BatchRequestDto batchRequestDto);

    void deleteBatch(Long batchId);
}
