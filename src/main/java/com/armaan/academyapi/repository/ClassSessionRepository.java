package com.armaan.academyapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.armaan.academyapi.entity.ClassSession;

import org.springframework.stereotype.Repository;
@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession,Long>{

    List<ClassSession> findByTimeTableBatchBatchId(Long batchId);

    List<ClassSession> findAllByDate(LocalDate date);

    List<ClassSession> findAllByTimeTableBatchBatchIdAndDate(Long batchId, LocalDate date);


@Query("""
    SELECT COALESCE(MAX(cs.slotIndex), 0)
    FROM ClassSession cs
    WHERE cs.timeTable.batch.batchId = :batchId
      AND cs.date = :date
""")
int findLastSlotIndex(Long timetableId, LocalDate date);

}
