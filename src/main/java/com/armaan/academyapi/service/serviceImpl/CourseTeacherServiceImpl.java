package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Course;
import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.entity.Teacher;
import com.armaan.academyapi.repository.CourseRepository;
import com.armaan.academyapi.repository.CourseTeacherRepository;
import com.armaan.academyapi.repository.TeacherRepository;
import com.armaan.academyapi.service.CourseTeacherService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CourseTeacherServiceImpl implements CourseTeacherService{

    private final CourseTeacherRepository courseTeacherRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public CourseTeacher assignTeacherToCourse(Long teacherId, Long courseId) {

        Course course=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Course Not Found"+courseId));
        Teacher teacher=teacherRepository.findById(teacherId).orElseThrow(()->new RuntimeException("Teacher Not Found"+teacherId));

        if(courseTeacherRepository.existsByCourseIdAndTeacherId(courseId,teacherId)){
                    throw new RuntimeException("Already assigned");
        }                                                                                                                                                                                                                                                                                                                                                                                                                                     
        CourseTeacher courseTeacher=new CourseTeacher();
        courseTeacher.setCourse(course);
        courseTeacher.setTeacher(teacher);
        return courseTeacherRepository.save(courseTeacher);
        
    }  

    @Override
    public void removeTeacherFromCourse(Long courseTeacherId) {
       
    }

    @Override
    public List<CourseTeacher> getAllCourseTeachers() {
        return courseTeacherRepository.findAll();
    }
}
