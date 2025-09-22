package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.entity.Teacher;
import com.armaan.academyapi.entity.TimeTable;
import com.armaan.academyapi.repository.CourseTeacherRepository;
import com.armaan.academyapi.repository.TeacherRepository;
import com.armaan.academyapi.repository.TimeTableRepository;
import com.armaan.academyapi.service.TeacherService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseTeacherRepository courseTeacherRepository;
    private final TimeTableRepository timeTableRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteTeacher(Long teacherId) {

        Teacher teacher=teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
        
                teacher.setDeleted(true);
        
        List<CourseTeacher> courseTeachers= courseTeacherRepository.findAllByTeacherTeacherId(teacherId);
        courseTeachers.forEach(cs->cs.setDeleted(true));

        List<TimeTable> tables=timeTableRepository.findAllByTeacherId(teacherId);
        tables.forEach(table->table.setDeleted(true));

    }

    @Override
    public Teacher updateTeacher(Long teacherId, Teacher updatedTeacher) {

        Teacher teacher=teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
        
        teacher.setContact(updatedTeacher.getContact());
        return teacher;
    }
}
