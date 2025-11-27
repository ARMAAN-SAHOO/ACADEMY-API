package com.armaan.academyapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.dto.request.CourseRequestDto;
import com.armaan.academyapi.dto.response.CourseResponseDto;
import com.armaan.academyapi.dto.update.CourseUpdateDto;
import com.armaan.academyapi.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDto> create(@Valid  @RequestBody CourseRequestDto courseRequestDto) {
        CourseResponseDto created=courseService.createCourse(courseRequestDto);
        return ResponseEntity
                .created(URI.create("/api/courses/" + created.getCourseId()))
                .body(created);
    }

    @PatchMapping("/{id}")
    public  ResponseEntity<CourseResponseDto> update(@PathVariable Long id,@Valid  @RequestBody CourseUpdateDto courseUpdateDto) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseUpdateDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAll() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
