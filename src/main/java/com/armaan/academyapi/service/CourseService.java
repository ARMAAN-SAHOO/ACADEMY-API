package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.Course;
import com.armaan.academyapi.entity.Teacher;

public interface CourseService {
    Course createCourse(Course course);
    Course getCourseById(Long courseId);
    List<Course> getAllCourses();
    Course updateCourse(Long courseId, Course updatedCourse);
    void deleteCourse(Long courseId);

    // extras
    List<Teacher> getTeachersForCourse(Long courseId);
}

