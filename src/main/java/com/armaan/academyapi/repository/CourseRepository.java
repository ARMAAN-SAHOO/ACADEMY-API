package com.armaan.academyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{

}
