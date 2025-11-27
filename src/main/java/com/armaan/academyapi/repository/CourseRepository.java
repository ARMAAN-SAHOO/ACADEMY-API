package com.armaan.academyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.armaan.academyapi.entity.Course;

import org.springframework.stereotype.Repository;
@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{

   @Query(value = "SELECT * FROM course WHERE name = :name LIMIT 1", nativeQuery = true)
Optional<Course> findByNameIncludingDeleted(@Param("name") String name);

}
