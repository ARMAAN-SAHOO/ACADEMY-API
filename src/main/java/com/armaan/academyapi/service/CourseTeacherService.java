package com.armaan.academyapi.service;


import java.util.List;

import com.armaan.academyapi.dto.request.CourseTeacherRequestDto;
import com.armaan.academyapi.dto.response.CourseTeacherResponseDto;

public interface CourseTeacherService {
    CourseTeacherResponseDto assignTeacherToCourse(CourseTeacherRequestDto courseTeacherRequestDto);
    CourseTeacherResponseDto getCourseTeacherById(Long courseTeacherId);
    void removeTeacherFromCourse(Long courseTeacherId);
    List<CourseTeacherResponseDto> getAllCourseTeachers();
}

