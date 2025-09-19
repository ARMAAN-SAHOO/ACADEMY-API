package com.armaan.academyapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.service.CourseTeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course-teachers")
public class CourseTeacherController {
    private final CourseTeacherService courseTeacherService;

    @PostMapping
    public ResponseEntity<CourseTeacher> create(@RequestBody CourseTeacher courseTeacher) {
        return ResponseEntity.ok(courseTeacherService.createCourseTeacher(courseTeacher));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseTeacher> get(@PathVariable Long id) {
        return ResponseEntity.ok(courseTeacherService.getCourseTeacherById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseTeacher>> getAll() {
        return ResponseEntity.ok(courseTeacherService.getAllCourseTeachers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseTeacherService.deleteCourseTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
