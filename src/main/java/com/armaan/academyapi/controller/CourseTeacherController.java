package com.armaan.academyapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.dto.response.CourseTeacherResponseDto;
import com.armaan.academyapi.service.CourseTeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course-teachers")
public class CourseTeacherController {
    private final CourseTeacherService courseTeacherService;

    @GetMapping
    public ResponseEntity<List<CourseTeacherResponseDto>> getAll() {
        return ResponseEntity.ok(courseTeacherService.getAllCourseTeachers());
    }
}
