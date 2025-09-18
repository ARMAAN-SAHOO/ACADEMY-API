package com.armaan.academyapi.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.entity.ClassSession;
import com.armaan.academyapi.entity.Student;
import com.armaan.academyapi.repository.BatchRepository;
import com.armaan.academyapi.service.BatchService;

import jakarta.persistence.EntityNotFoundException;
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
    public Batch updateBatch(Long batchId, Batch updatedBatch) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBatch'");
    }

    @Override
    public List<Student> getStudentsInBatch(Long batchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsInBatch'");
    }

    @Override
    public List<ClassSession> getSessions(Long batchId, LocalDate date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessions'");
    }
}
