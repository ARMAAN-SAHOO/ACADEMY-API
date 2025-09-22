package com.armaan.academyapi.service;
import java.util.List;

import com.armaan.academyapi.entity.Batch;

public interface BatchService {
    Batch createBatch(Batch batch);
    Batch getBatchById(Long batchId);
    List<Batch> getAllBatches();
    Batch updateBatch(Long batchId, Batch updatedBatch);
    void deleteBatch(Long batchId);
}
