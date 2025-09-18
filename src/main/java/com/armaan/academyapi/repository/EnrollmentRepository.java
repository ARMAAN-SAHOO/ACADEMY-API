package com.armaan.academyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Enrollment;

import org.springframework.stereotype.Repository;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{

}
