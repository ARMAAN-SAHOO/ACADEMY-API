package com.armaan.academyapi.service.serviceImpl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.repository.BatchRepository;
import com.armaan.academyapi.service.BatchService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {
    private final BatchRepository batchRepository;

    @Override
    public Batch createBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public Batch getBatchById(Long batchId) {
        return batchRepository.findById(batchId)
                .orElseThrow(() -> new EntityNotFoundException("Batch not found"));
    }

    @Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    @Override
    public void deleteBatch(Long batchId) {
        batchRepository.deleteById(batchId);
    }

    @Override
    @Transactional
    public Batch updateBatch(Long batchId, Batch updatedBatch) {
        Batch batch =batchRepository.findById(batchId)
                .orElseThrow(() -> new EntityNotFoundException("Batch not found"));
        batch.setName(updatedBatch.getName());
        return batch;
    }
}
