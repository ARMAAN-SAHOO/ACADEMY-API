package com.armaan.academyapi.service;

import java.time.LocalDate;
import java.util.List;

import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.entity.ClassSession;
import com.armaan.academyapi.entity.Student;

public interface BatchService {
    Batch createBatch(Batch batch);
    Batch getBatchById(Long batchId);
    List<Batch> getAllBatches();
    Batch updateBatch(Long batchId, Batch updatedBatch);
    void deleteBatch(Long batchId);

    // extras
    List<Student> getStudentsInBatch(Long batchId);
    List<ClassSession> getSessions(Long batchId, LocalDate date);
}
