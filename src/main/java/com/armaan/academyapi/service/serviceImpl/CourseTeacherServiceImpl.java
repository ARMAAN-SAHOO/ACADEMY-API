package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.repository.CourseTeacherRepository;
import com.armaan.academyapi.service.CourseTeacherService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CourseTeacherServiceImpl implements CourseTeacherService{

    private final CourseTeacherRepository courseTeacherRepository;

    @Override
    public CourseTeacher assignTeacherToCourse(Long teacherId, Long courseId) {
        CourseTeacher courseTeacher=new CourseTeacher();
        courseTeacher.setCourse(null);
        courseTeacher.setTeacher(null);
        courseTeacherRepository.save(courseTeacher);
        return courseTeacher;
    }

    @Override
    public void removeTeacherFromCourse(Long courseTeacherId) {
       
    }

    @Override
    public List<CourseTeacher> getAllCourseTeachers() {
        return courseTeacherRepository.findAll();
    }
}
