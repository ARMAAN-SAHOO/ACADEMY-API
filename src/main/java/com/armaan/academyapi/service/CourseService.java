package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.CourseRequestDto;
import com.armaan.academyapi.dto.response.CourseResponseDto;
import com.armaan.academyapi.dto.update.CourseUpdateDto;

public interface CourseService {
    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);
    CourseResponseDto getCourseById(Long courseId);
    List<CourseResponseDto> getAllCourses();
    CourseResponseDto updateCourse(Long courseId, CourseUpdateDto courseUpdateDto);
    void deleteCourse(Long courseId);
}

