package com.armaan.academyapi.service.serviceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.ClassSession;
import com.armaan.academyapi.entity.TimeTable;
import com.armaan.academyapi.repository.TimeTableRepository;
import com.armaan.academyapi.service.TimeTableService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {
    private final TimeTableRepository timeTableRepository;

    @Override
    public TimeTable createTimeTable(TimeTable timeTable) {
        return timeTableRepository.save(timeTable);
    }

    @Override
    public TimeTable getTimeTableById(Long timetableId) {
        return timeTableRepository.findById(timetableId)
                .orElseThrow(() -> new EntityNotFoundException("TimeTable not found"));
    }

    @Override
    public List<TimeTable> getTimeTablesByBatch(Long batchId) {
        return timeTableRepository.findByBatchId(batchId);
    }

    @Override
    public List<TimeTable> getTimeTablesByCourse(Long courseId) {
        return timeTableRepository.findByCourseId(courseId);
    }

    @Override
    public List<TimeTable> getTimeTablesByDay(DayOfWeek dayOfWeek) {
        return timeTableRepository.findByDayOfWeek(dayOfWeek);
    }

    @Override
    public void deleteTimeTable(Long timetableId) {
        timeTableRepository.deleteById(timetableId);
    }

    @Override
    public List<TimeTable> getTimeTablesForBatch(Long batchId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimeTablesForBatch'");
    }

    @Override
    public List<TimeTable> getTimeTablesForCourse(Long courseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimeTablesForCourse'");
    }

    @Override
    public List<ClassSession> generateSessionsForDateRange(Long batchId, LocalDate start, LocalDate end) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateSessionsForDateRange'");
    }
}

