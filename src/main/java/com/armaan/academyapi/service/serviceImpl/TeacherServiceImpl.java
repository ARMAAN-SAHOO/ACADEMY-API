package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.TeacherRequestDto;
import com.armaan.academyapi.dto.response.TeacherResponseDto;
import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.entity.Teacher;
import com.armaan.academyapi.entity.TimeTable;
import com.armaan.academyapi.mapper.TeacherMapper;
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
    private final TeacherMapper teacherMapper;

    @Override
    @Transactional
    public TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto) {
        Teacher teacher=teacherMapper.toEntity(teacherRequestDto);
        Teacher savedTeacher= teacherRepository.save(teacher);
        return teacherMapper.toResponseDto(savedTeacher);
    }

    @Override
    public TeacherResponseDto getTeacherById(Long teacherId) {
        Teacher teacher= teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
                return teacherMapper.toResponseDto(teacher);
    }

    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        return teacherRepository.findAll().stream().map(teacher->teacherMapper.toResponseDto(teacher)).toList();
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
    @Transactional
    public TeacherResponseDto updateTeacher(Long teacherId, TeacherRequestDto teacherRequestDto) {

        Teacher teacher=teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));
        
        teacherMapper.update(teacherRequestDto, teacher);
        return teacherMapper.toResponseDto(teacher);
    }
}
