package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.StudentRequestDto;
import com.armaan.academyapi.dto.response.StudentResponseDto;
import com.armaan.academyapi.dto.update.StudentUpdateDto;

public interface StudentService {
    
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);
    StudentResponseDto getStudentById(Long studentId);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto updateStudent(Long studentId, StudentUpdateDto studentUpdateDto);
    void deleteStudent(Long studentId);
}

