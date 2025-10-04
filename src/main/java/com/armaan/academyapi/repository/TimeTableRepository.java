package com.armaan.academyapi.repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.armaan.academyapi.entity.Batch;
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

boolean existsByBatchAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(
    Batch batch,
    DayOfWeek dayOfWeek,
    LocalTime startTime,
    LocalTime endTime
);

boolean existsByBatchAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThanAndTimetableIdNot(
    Batch batch,
    DayOfWeek dayOfWeek,
    LocalTime startTime,
    LocalTime endTime,
    Long timetableId
);


    List<TimeTable> findAllByBatchBatchIdOrderByDayOfWeekAscStartTimeAsc(Long batchId);


    @Query("SELECT COUNT(t) FROM TimeTable t WHERE t.batch.batchId=:batchId"+
    " AND t.dayOfWeek = :dayOfWeek AND t.deleted = false")
    int countByBatchAndDayOfWeek(@Param("batchId") Long batchId,@Param("dayOfWeek") DayOfWeek dayOfWeek);

}
