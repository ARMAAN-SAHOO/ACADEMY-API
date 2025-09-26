package com.armaan.academyapi.service;
import java.util.List;

import com.armaan.academyapi.dto.request.BatchRequestDto;
import com.armaan.academyapi.dto.response.BatchResponseDto;
import com.armaan.academyapi.dto.update.BatchUpdateDto;

public interface BatchService {
    BatchResponseDto createBatch(BatchRequestDto batchRequestDto);
    BatchResponseDto getBatchById(Long batchId);
    List<BatchResponseDto> getAllBatches();
    BatchResponseDto updateBatch(Long batchId, BatchUpdateDto batchUpdateDto);

    void deleteBatch(Long batchId);
}
