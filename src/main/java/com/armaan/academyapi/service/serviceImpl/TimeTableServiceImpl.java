package com.armaan.academyapi.service.serviceImpl;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.TimeTableRequestDto;
import com.armaan.academyapi.dto.response.TimeTableResponseDto;
import com.armaan.academyapi.dto.update.TimeTableUpdateDto;
import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.entity.CourseTeacher;
import com.armaan.academyapi.entity.TimeTable;
import com.armaan.academyapi.mapper.TimeTableMapper;
import com.armaan.academyapi.repository.BatchRepository;
import com.armaan.academyapi.repository.CourseTeacherRepository;
import com.armaan.academyapi.repository.TimeTableRepository;
import com.armaan.academyapi.service.TimeTableService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepository timeTableRepository;
    private final BatchRepository batchRepository;
    private final CourseTeacherRepository courseTeacherRepository;
    private final TimeTableMapper timeTableMapper;

    @Transactional
    @Override
    public TimeTableResponseDto createTimeTable(TimeTableRequestDto timeTableRequestDto) {

        Batch batch = batchRepository.findById(timeTableRequestDto.getBatchId())
                .orElseThrow(() -> new EntityNotFoundException());
        CourseTeacher courseTeacher = courseTeacherRepository.findById(timeTableRequestDto.getCourseteacherId())
                .orElseThrow(() -> new EntityNotFoundException());

        boolean conflict = timeTableRepository.existsByBatchAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(
                batch,
                timeTableRequestDto.getDayOfWeek(),
                timeTableRequestDto.getStartTime(),
                timeTableRequestDto.getEndTime());
        if (conflict) {
            throw new IllegalArgumentException("Time slot overlaps with an existing timetable for this batch");
        }

        TimeTable timeTable = timeTableMapper.toEntity(timeTableRequestDto);
        timeTable.setBatch(batch);
        timeTable.setCourseTeacher(courseTeacher);
        TimeTable savedTable = timeTableRepository.save(timeTable);
        return timeTableMapper.toResponseDto(savedTable);
    }

    @Override
    public TimeTableResponseDto getTimeTableById(Long timetableId) {
        TimeTable timeTable = timeTableRepository.findById(timetableId)
                .orElseThrow(() -> new EntityNotFoundException("TimeTable not found"));
        return timeTableMapper.toResponseDto(timeTable);
    }

    @Override
    public List<TimeTableResponseDto> getTimeTablesForBatch(Long batchId) {

        return timeTableRepository.findAllByBatchBatchIdOrderByDayOfWeekAscStartTimeAsc(batchId).stream()
                .map(timeTableMapper::toResponseDto).toList();
    }

    @Transactional
    @Override
    public TimeTableResponseDto updateTimeTable(Long timetableId, TimeTableUpdateDto timeTableUpdateDto) {

        TimeTable timeTable = timeTableRepository.findById(timetableId)
                .orElseThrow(() -> new EntityNotFoundException("TimeTable not found"));

        // Check if batch changed
        if (timeTableUpdateDto.getBatchId() != null
                && !timeTable.getBatch().getBatchId().equals(timeTableUpdateDto.getBatchId())) {
            Batch newBatch = batchRepository.findById(timeTableUpdateDto.getBatchId())
                    .orElseThrow(() -> new EntityNotFoundException("Batch not found"));
            timeTable.setBatch(newBatch);
        }

        // Check if courseTeacher changed
        if (timeTableUpdateDto.getCourseteacherId() != null && !timeTable.getCourseTeacher().getCourseTeacherId()
                .equals(timeTableUpdateDto.getCourseteacherId())) {
            CourseTeacher newCT = courseTeacherRepository.findById(timeTableUpdateDto.getCourseteacherId())
                    .orElseThrow(() -> new EntityNotFoundException("CourseTeacher not found"));
            timeTable.setCourseTeacher(newCT);
        }

        DayOfWeek day = timeTableUpdateDto.getDayOfWeek() != null ? timeTableUpdateDto.getDayOfWeek()
                : timeTable.getDayOfWeek();
        LocalTime start = timeTableUpdateDto.getStartTime() != null ? timeTableUpdateDto.getStartTime()
                : timeTable.getStartTime();
        LocalTime end = timeTableUpdateDto.getEndTime() != null ? timeTableUpdateDto.getEndTime()
                : timeTable.getEndTime();

        boolean conflict = timeTableRepository
                .existsByBatchAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThanAndTimetableIdNot(
                        timeTable.getBatch(),
                        day,
                        start,
                        end,
                        timetableId);

        if (conflict) {
            throw new IllegalArgumentException("Time slot overlaps with an existing timetable for this batch");
        }

        timeTableMapper.update(timeTableUpdateDto, timeTable);
        return timeTableMapper.toResponseDto(timeTable);

    }

    @Override
    @Transactional
    public void deleteTimeTable(Long timetableId) {
        TimeTable timeTable = timeTableRepository.findById(timetableId)
                .orElseThrow(() -> new EntityNotFoundException("TimeTable not found"));
        timeTable.setDeleted(true);
    }
}
