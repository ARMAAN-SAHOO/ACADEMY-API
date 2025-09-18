package com.armaan.academyapi.service;

import java.time.LocalDate;
import java.util.List;

import com.armaan.academyapi.entity.ClassSession;
import com.armaan.academyapi.entity.TimeTable;

public interface TimeTableService {
    TimeTable createTimeTable(TimeTable timetable);
    TimeTable getTimeTableById(Long timetableId);
    List<TimeTable> getTimeTablesForBatch(Long batchId);
    void deleteTimeTable(Long timetableId);

    // Extras
    List<TimeTable> getTimeTablesForCourse(Long courseId);
    List<ClassSession> generateSessionsForDateRange(Long batchId, LocalDate start, LocalDate end);
}

