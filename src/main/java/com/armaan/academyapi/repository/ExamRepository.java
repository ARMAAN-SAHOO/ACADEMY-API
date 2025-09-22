package com.armaan.academyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Exam;

import org.springframework.stereotype.Repository;
@Repository
public interface ExamRepository extends JpaRepository<Exam,Long>{

    List<Exam> findByBatchBatchId(Long batchId);

    List<Exam> findAllByBatchBatchId(Long batchId);

    List<Exam> findAllByCourseCourseId(Long courseId);

}
