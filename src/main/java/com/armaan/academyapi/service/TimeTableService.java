package com.armaan.academyapi.service;

import java.util.List;
import com.armaan.academyapi.entity.TimeTable;

public interface TimeTableService {
    TimeTable createTimeTable(TimeTable timetable);
    TimeTable updateTimeTable(TimeTable timeTable);
    TimeTable getTimeTableById(Long timetableId);
    List<TimeTable> getTimeTablesForBatch(Long batchId);
    void deleteTimeTable(Long timetableId);
}

