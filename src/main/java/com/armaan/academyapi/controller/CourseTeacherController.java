package com.armaan.academyapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.dto.request.CourseTeacherRequestDto;
import com.armaan.academyapi.dto.response.CourseTeacherResponseDto;
import com.armaan.academyapi.service.CourseTeacherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course-teachers")
public class CourseTeacherController {

    private final CourseTeacherService courseTeacherService;

    @GetMapping
    public ResponseEntity<List<CourseTeacherResponseDto>> getAll() {
        return ResponseEntity.ok(courseTeacherService.getAllCourseTeachers());
    }

    @PostMapping
    public ResponseEntity<CourseTeacherResponseDto> createAssignment(@RequestBody CourseTeacherRequestDto courseTeacherRequestDto) {
       return ResponseEntity.ok(courseTeacherService.assignTeacherToCourse(courseTeacherRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseTeacherResponseDto> getAssignmentById(@PathVariable("id") Long courseTeacherId) {
        return ResponseEntity.ok(courseTeacherService.getCourseTeacherById(courseTeacherId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignmentById(@PathVariable("id") Long courseTeacherId) {
        courseTeacherService.removeTeacherFromCourse(courseTeacherId);
        return ResponseEntity.noContent().build();
    }
    
}
