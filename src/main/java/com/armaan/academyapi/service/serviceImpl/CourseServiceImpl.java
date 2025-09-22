package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Course;
import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.entity.Exam;
import com.armaan.academyapi.entity.TimeTable;
import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.repository.CourseRepository;
import com.armaan.academyapi.repository.CourseTeacherRepository;
import com.armaan.academyapi.repository.ExamRepository;
import com.armaan.academyapi.repository.TimeTableRepository;
import com.armaan.academyapi.service.CourseService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseTeacherRepository courseTeacherRepository;
    private final TimeTableRepository timeTableRepository;
    private final ExamRepository examRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId) {
        
        Course course=courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        course.setDeleted(true);

        List<CourseTeacher> courseTeachers= courseTeacherRepository.findAllByCourseCourseId(courseId);
        courseTeachers.forEach(cs->cs.setDeleted(true));

        List<TimeTable> tables=timeTableRepository.findAllByCourseId(courseId);
        tables.forEach(table->table.setDeleted(true));

        List<Exam> exams=examRepository.findAllByCourseCourseId(courseId);
        exams.forEach(exam->exam.setStatus(ExamStatus.CANCELLED));
    }

    @Override
    @Transactional
    public Course updateCourse(Long courseId, Course updatedCourse) {

        Course course=courseRepository.findById(courseId)
                    .orElseThrow(()->new RuntimeException("Course Not Found"));

        course.setName(updatedCourse.getName());
        course.setDescription(updatedCourse.getDescription());

        return course;
    }
}
