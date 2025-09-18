package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.CourseTeacher;

public interface CourseTeacherService {
    CourseTeacher assignTeacherToCourse(Long teacherId, Long courseId);
    void removeTeacherFromCourse(Long courseTeacherId);
    List<CourseTeacher> getAssignmentsByCourse(Long courseId);
    List<CourseTeacher> getAssignmentsByTeacher(Long teacherId);
}

