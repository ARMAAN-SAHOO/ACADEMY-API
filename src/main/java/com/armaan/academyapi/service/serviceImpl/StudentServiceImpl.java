package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.entity.Parent;
import com.armaan.academyapi.entity.Student;
import com.armaan.academyapi.repository.EnrollmentRepository;
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

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long studentId, Student studentDetails) {
        Student student = getStudentById(studentId);
        student.setFullName(studentDetails.getFullName());
        student.setPhone(studentDetails.getPhone());
        student.setParent(studentDetails.getParent());
        return studentRepository.save(student);
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

