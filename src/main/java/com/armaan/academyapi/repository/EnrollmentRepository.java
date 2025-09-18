package com.armaan.academyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long>{

}
