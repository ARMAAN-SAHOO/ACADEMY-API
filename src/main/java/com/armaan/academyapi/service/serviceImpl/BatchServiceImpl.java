package com.armaan.academyapi.service.serviceImpl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.entity.Exam;
import com.armaan.academyapi.entity.TimeTable;
import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.repository.BatchRepository;
import com.armaan.academyapi.repository.EnrollmentRepository;
import com.armaan.academyapi.repository.ExamRepository;
import com.armaan.academyapi.repository.TimeTableRepository;
import com.armaan.academyapi.service.BatchService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final TimeTableRepository timeTableRepository;
    private final ExamRepository examRepository;

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
    @Transactional
    public void deleteBatch(Long batchId) {

        Batch batch=batchRepository.findById(batchId)
                .orElseThrow(() -> new EntityNotFoundException("Batch not found"));
        
        batch.setDeleted(true);

        List<Enrollment> enrollments=enrollmentRepository.findAllByBatchBatchId(batchId);
        enrollments.forEach(enrollment -> enrollment.setDeleted(true));

        List<TimeTable> tables=timeTableRepository.findAllByBatchBatchId(batchId);
        tables.forEach(table -> table.setDeleted(true));

        //if user agrees
        List<Exam> exams=examRepository.findAllByBatchBatchId(batchId);
        exams.forEach(exam->exam.setStatus(ExamStatus.CANCELLED));


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
