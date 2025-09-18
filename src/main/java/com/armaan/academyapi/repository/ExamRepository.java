package com.armaan.academyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Exam;

import org.springframework.stereotype.Repository;
@Repository
public interface ExamRepository extends JpaRepository<Exam,Long>{

}
