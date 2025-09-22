package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.Teacher;

public interface TeacherService {

    Teacher createTeacher(Teacher teacher);
    Teacher getTeacherById(Long teacherId);
    List<Teacher> getAllTeachers();
    Teacher updateTeacher(Long teacherId, Teacher updatedTeacher);
    void deleteTeacher(Long teacherId);
}


