package com.armaan.academyapi.service;

import java.util.List;
import com.armaan.academyapi.entity.Student;

public interface StudentService {
    
    Student createStudent(Student student);
    Student getStudentById(Long studentId);
    List<Student> getAllStudents();
    Student updateStudent(Long studentId, Student updatedStudent);
    void deleteStudent(Long studentId);
}

