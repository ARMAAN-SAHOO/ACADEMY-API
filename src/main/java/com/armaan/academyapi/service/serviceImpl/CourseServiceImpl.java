package com.armaan.academyapi.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.CourseRequestDto;
import com.armaan.academyapi.dto.response.CourseResponseDto;
import com.armaan.academyapi.dto.update.CourseUpdateDto;
import com.armaan.academyapi.entity.Course;
import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.entity.Exam;
import com.armaan.academyapi.entity.TimeTable;
import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.exception.BusinessException;
import com.armaan.academyapi.exception.ResourceNotFoundException;
import com.armaan.academyapi.mapper.CourseMapper;
import com.armaan.academyapi.repository.CourseRepository;
import com.armaan.academyapi.repository.CourseTeacherRepository;
import com.armaan.academyapi.repository.ExamRepository;
import com.armaan.academyapi.repository.TimeTableRepository;
import com.armaan.academyapi.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final CourseTeacherRepository courseTeacherRepository;
    private final TimeTableRepository timeTableRepository;
    private final ExamRepository examRepository;

@Override
@Transactional
public CourseResponseDto createCourse(CourseRequestDto request) {

    // Step 1: Look for existing course, including soft-deleted
    Optional<Course> existing = courseRepository.findByNameIncludingDeleted(request.getName());

    if (existing.isPresent()) {
        Course course = existing.get();

        if (course.isDeleted()) {
            // Step 2: Restore soft-deleted course
            course.setDeleted(false);
            course.setDescription(request.getDescription());
            Course savedCourse = courseRepository.save(course);
            return courseMapper.toResponseDto(savedCourse);
        } else {
            // Step 3: Active course exists → duplicate
            throw new BusinessException("Course already exists: " + request.getName());
        }
    }

    // Step 4: No existing course → create new
    Course newCourse = new Course();
    newCourse.setName(request.getName());
    newCourse.setDescription(request.getDescription());
    newCourse.setDeleted(false);
    Course savedCourse = courseRepository.save(newCourse);
    return courseMapper.toResponseDto(savedCourse);
}


    @Override
    public CourseResponseDto getCourseById(Long courseId) {

        Course course=courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));
        
        return courseMapper.toResponseDto(course);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {

        List<Course> courses= courseRepository.findAll();

        return courses.stream().map(course->courseMapper.toResponseDto(course)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId) {
        
        Course course=courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

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
    public CourseResponseDto updateCourse(Long courseId, CourseUpdateDto courseUpdateDto) {

        Course course=courseRepository.findById(courseId)
                    .orElseThrow(()->new ResourceNotFoundException("Course", courseId));

        courseMapper.update(courseUpdateDto, course);
        return courseMapper.toResponseDto(course);
    }
}
