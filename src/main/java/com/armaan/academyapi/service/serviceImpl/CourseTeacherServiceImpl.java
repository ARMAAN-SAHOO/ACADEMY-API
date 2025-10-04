package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.CourseTeacherRequestDto;
import com.armaan.academyapi.dto.response.CourseTeacherResponseDto;
import com.armaan.academyapi.entity.Course;
import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.entity.Teacher;
import com.armaan.academyapi.entity.TimeTable;
import com.armaan.academyapi.exception.BusinessException;
import com.armaan.academyapi.exception.ResourceNotFoundException;
import com.armaan.academyapi.mapper.CourseTeacherMapper;
import com.armaan.academyapi.repository.CourseRepository;
import com.armaan.academyapi.repository.CourseTeacherRepository;
import com.armaan.academyapi.repository.TeacherRepository;
import com.armaan.academyapi.repository.TimeTableRepository;
import com.armaan.academyapi.service.CourseTeacherService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CourseTeacherServiceImpl implements CourseTeacherService{

    private final CourseTeacherRepository courseTeacherRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final TimeTableRepository timeTableRepository;
    private final CourseTeacherMapper courseTeacherMapper;

    @Override
    @Transactional
    public CourseTeacherResponseDto assignTeacherToCourse(CourseTeacherRequestDto courseTeacherRequestDto) {

        Course course=courseRepository.findById(courseTeacherRequestDto.getCourseId())
        .orElseThrow(()->new ResourceNotFoundException("Course", courseTeacherRequestDto.getCourseId()));

        Teacher teacher=teacherRepository.findById(courseTeacherRequestDto.getTeacherId())
        .orElseThrow(()->new ResourceNotFoundException("Teacher", courseTeacherRequestDto.getTeacherId()));

        if(courseTeacherRepository.existsByCourseCourseIdAndTeacherTeacherId(courseTeacherRequestDto.getCourseId(),courseTeacherRequestDto.getTeacherId())){
                    throw new BusinessException("Already assigned the teacher to this course");
        }    

        CourseTeacher courseTeacher =courseTeacherMapper.toEntity(courseTeacherRequestDto);       
        courseTeacher.setCourse(course);
        courseTeacher.setTeacher(teacher);
        CourseTeacher savedCourseTeacher=courseTeacherRepository.save(courseTeacher);
        return courseTeacherMapper.toResponseDto(savedCourseTeacher);
        
    }  

    @Override
    @Transactional
    public void removeTeacherFromCourse(Long courseTeacherId) {

        CourseTeacher courseTeacher=courseTeacherRepository.findById(courseTeacherId)
        .orElseThrow(()->new ResourceNotFoundException("CourseTeacher", courseTeacherId));

        courseTeacher.setDeleted(true);

        List<TimeTable> tables=timeTableRepository.findAllByCourseTeacherCourseTeacherId(courseTeacherId);
        tables.forEach(timetable->timetable.setDeleted(true));
    }

    

    @Override
    public List<CourseTeacherResponseDto> getAllCourseTeachers() {
        return courseTeacherRepository.findAll().stream().map(courseTeacherMapper::toResponseDto).toList();
    }

    @Override
    public CourseTeacherResponseDto getCourseTeacherById(Long courseTeacherId) {
        CourseTeacher courseTeacher=courseTeacherRepository.findById(courseTeacherId)
        .orElseThrow(()->new ResourceNotFoundException("Teacher assigned to course", courseTeacherId));
        return courseTeacherMapper.toResponseDto(courseTeacher);
    }
}
