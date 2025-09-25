package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.TeacherRequestDto;
import com.armaan.academyapi.dto.response.TeacherResponseDto;

public interface TeacherService {

    TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto);
    TeacherResponseDto getTeacherById(Long teacherId);
    List<TeacherResponseDto> getAllTeachers();
    TeacherResponseDto updateTeacher(Long teacherId, TeacherRequestDto teacherRequestDto);
    void deleteTeacher(Long teacherId);
}


