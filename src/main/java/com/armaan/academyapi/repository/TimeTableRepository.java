package com.armaan.academyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.armaan.academyapi.entity.TimeTable;

import org.springframework.stereotype.Repository;
@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable,Long>{

    List<TimeTable> findAllByBatchBatchId(Long batchId);

    List<TimeTable> findAllByCourseTeacherCourseTeacherId(Long courseTeacherId);

    @Query("SELECT t FROM TimeTable t WHERE t.courseTeacher.course.courseId = :courseId")
    List<TimeTable> findAllByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT t FROM TimeTable t WHERE t.courseTeacher.teacher.teacherId =:teacherId")
    List<TimeTable> findAllByTeacherId(@Param("teacherId") Long teacherId);

}
