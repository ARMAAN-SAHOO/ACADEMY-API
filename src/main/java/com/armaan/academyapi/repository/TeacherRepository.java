package com.armaan.academyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long>{

}
