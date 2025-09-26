package com.armaan.academyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Enrollment;

import org.springframework.stereotype.Repository;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{

    List<Enrollment> findAllByStudentStudentId(Long studentId);

    List<Enrollment> findAllByBatchBatchId(Long batchId);

    boolean existsByBatchBatchIdAndStudenttudentId(Long batchId, Long studentId);

}
