package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
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
    public List<TimeTable> getTimeTablesForBatch(Long batchId) {

        return timeTableRepository.findAllByBatchId(batchId);
    }

    @Override
    public TimeTable updateTimeTable(TimeTable timeTable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTimeTable'");
    }

    @Override
    public void deleteTimeTable(Long timetableId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTimeTable'");
    }
}

