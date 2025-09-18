package com.armaan.academyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.ClassSession;

import org.springframework.stereotype.Repository;
@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession,Long>{

    List<ClassSession> findByBatchId(Long batchId);

}
