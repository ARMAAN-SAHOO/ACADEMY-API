package com.armaan.academyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.TimeTable;

import org.springframework.stereotype.Repository;
@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable,Long>{

    List<TimeTable> findByBatchId(Long batchId);

    List<TimeTable> findAllByBatchId(Long batchId);

    List<TimeTable> findAllByBatchBatchId(Long batchId);

    List<TimeTable> findAllByCourseCourseId(Long courseId);

    List<TimeTable> findAllByTeacherTeacherId(Long teacherId);

    List<TimeTable> findAllByCourseTeacherCourseTeacherId(Long courseTeacherId);

}
