package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Course;
import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.entity.Teacher;
import com.armaan.academyapi.entity.TimeTable;
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

    @Override
    public CourseTeacher assignTeacherToCourse(Long teacherId, Long courseId) {

        Course course=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Course Not Found"+courseId));
        Teacher teacher=teacherRepository.findById(teacherId).orElseThrow(()->new RuntimeException("Teacher Not Found"+teacherId));

        if(courseTeacherRepository.existsByCourseCourseIdAndTeacherTeacherId(courseId,teacherId)){
                    throw new RuntimeException("Already assigned");
        }                                                                                                                                                                                                                                                                                                                                                                                                                                     
        CourseTeacher courseTeacher=new CourseTeacher();
        courseTeacher.setCourse(course);
        courseTeacher.setTeacher(teacher);
        return courseTeacherRepository.save(courseTeacher);
        
    }  

    @Override
    @Transactional
    public void removeTeacherFromCourse(Long courseTeacherId) {

        CourseTeacher courseTeacher=courseTeacherRepository.findById(courseTeacherId)
        .orElseThrow(()->new RuntimeException("Not Found"));

        courseTeacher.setDeleted(true);

        List<TimeTable> tables=timeTableRepository.findAllByCourseTeacherCourseTeacherId(courseTeacherId);
        tables.forEach(timetable->timetable.setDeleted(true));
    }

    

    @Override
    public List<CourseTeacher> getAllCourseTeachers() {
        return courseTeacherRepository.findAll();
    }

    @Override
    public CourseTeacher getCourseTeacherById(Long courseTeacherId) {
        CourseTeacher courseTeacher=courseTeacherRepository.findById(courseTeacherId)
        .orElseThrow(()->new RuntimeException("Not Found"));
        return courseTeacher;
    }
}
