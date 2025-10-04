package com.armaan.academyapi.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.entity.Exam;
import com.armaan.academyapi.enums.ExamStatus;

import org.springframework.stereotype.Repository;
@Repository
public interface ExamRepository extends JpaRepository<Exam,Long>{

    List<Exam> findByBatchBatchId(Long batchId);

    List<Exam> findAllByBatchBatchId(Long batchId);

    List<Exam> findAllByCourseCourseId(Long courseId);

    boolean existsByBatchAndDateAndStartTimeLessThanAndEndTimeGreaterThan(
    Batch batch,
    LocalDate date,
    LocalTime endTime,
    LocalTime startTime
);

    List<Exam> findByStatus(ExamStatus scheduled);


}
