package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.StudentRequestDto;
import com.armaan.academyapi.dto.response.StudentResponseDto;
import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.entity.Parent;
import com.armaan.academyapi.entity.Student;
import com.armaan.academyapi.mapper.StudentMapper;
import com.armaan.academyapi.repository.EnrollmentRepository;
import com.armaan.academyapi.repository.ParentRepository;
import com.armaan.academyapi.repository.StudentRepository;
import com.armaan.academyapi.service.StudentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ParentRepository parentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        Student student=studentMapper.toEntity(studentRequestDto);
        Parent parent=parentRepository.findById(studentRequestDto.getParentId()).orElseThrow(()->new EntityNotFoundException());
        student.setParent(parent);
        Student savedStudent=studentRepository.save(student);
        return studentMapper.toResponseDto(savedStudent);
    }

    @Override
    public StudentResponseDto getStudentById(Long studentId) {
        Student student =studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        return studentMapper.toResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return  studentRepository.findAll().stream().map(student->studentMapper.toResponseDto(student)).toList();
    }

    @Override
    public StudentResponseDto updateStudent(Long studentId, StudentRequestDto studentRequestDto) {

        Student student=studentRepository.findById(studentId).orElseThrow(()->new EntityNotFoundException());
        studentMapper.update(studentRequestDto, student);
        return studentMapper.toResponseDto(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) {

       Student student= studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        student.setDeleted(true);

        List<Enrollment> enrollments=enrollmentRepository.findAllByStudentStudentId(studentId);
        enrollments.forEach(enrollment -> enrollment.setDeleted(true));

        Parent parent=student.getParent();
        if(parent!=null){
            boolean hasActiveChild=parent.getStudents().stream().anyMatch(s->!s.isDeleted());

            if(!hasActiveChild){
                parent.setDeleted(true);
            }
        }

    }
}

