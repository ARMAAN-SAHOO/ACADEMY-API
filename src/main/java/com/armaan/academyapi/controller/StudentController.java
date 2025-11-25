package com.armaan.academyapi.controller;

import com.armaan.academyapi.dto.request.StudentRequestDto;
import com.armaan.academyapi.dto.response.StudentResponseDto;
import com.armaan.academyapi.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDto> create(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto student = studentService.createStudent(studentRequestDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> get(@PathVariable Long id) {
        StudentResponseDto student = studentService.getStudentById(id);
        if (student == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAll() {
        List<StudentResponseDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
    }
}












