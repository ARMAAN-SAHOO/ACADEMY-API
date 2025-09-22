package com.armaan.academyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.CourseTeacher;

import org.springframework.stereotype.Repository;
@Repository
public interface CourseTeacherRepository extends JpaRepository<CourseTeacher,Long>{

    boolean existsByCourseCourseIdAndTeacherTeacherId(Long courseId, Long teacherId);

    List<CourseTeacher> findAllByCourseCourseId(Long courseId);

    List<CourseTeacher> findAllByTeacherTeacherId(Long teacherId);

}
