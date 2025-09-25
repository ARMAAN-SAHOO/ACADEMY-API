package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.TimeTableRequestDto;
import com.armaan.academyapi.dto.response.TimeTableResponseDto;
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

    @Override
    public TimeTableResponseDto createTimeTable(TimeTableRequestDto timeTableRequestDto) {

        Batch batch=batchRepository.findById(timeTableRequestDto.getBatchId()).orElseThrow(()->new EntityNotFoundException());
        CourseTeacher courseTeacher=courseTeacherRepository.findById(timeTableRequestDto.getCourseteacherId()).orElseThrow(()->new EntityNotFoundException());

        boolean conflict=timeTableRepository.existsByBatchAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThan(
                batch,
            timeTableRequestDto.getDayOfWeek(),
            timeTableRequestDto.getStartTime(),
            timeTableRequestDto.getEndTime()
        );
        if (conflict) {
        throw new IllegalArgumentException("Time slot overlaps with an existing timetable for this batch");
        }
        
        TimeTable timeTable=timeTableMapper.toEntity(timeTableRequestDto);
        timeTable.setBatch(batch);
        timeTable.setCourseTeacher(courseTeacher);
        TimeTable savedTable =timeTableRepository.save(timeTable);
        return timeTableMapper.toResponseDto(savedTable);
    }

    @Override
    public TimeTableResponseDto getTimeTableById(Long timetableId) {
        TimeTable timeTable= timeTableRepository.findById(timetableId)
                .orElseThrow(() -> new EntityNotFoundException("TimeTable not found"));
        return timeTableMapper.toResponseDto(timeTable);
    }

    @Override
    public List<TimeTableResponseDto> getTimeTablesForBatch(Long batchId) {

        return timeTableRepository.findAllByBatchBatchIdOrderByDayOfWeekAscStartTimeAsc(batchId).stream().map(timeTableMapper::toResponseDto).toList();
    }

    @Override
    public TimeTableResponseDto updateTimeTable(Long timetableId,TimeTableRequestDto timeTableRequestDto) {
        TimeTable timeTable= timeTableRepository.findById(timetableId)
                .orElseThrow(() -> new EntityNotFoundException("TimeTable not found"));


        // Check if batch changed
if (!timeTable.getBatch().getBatchId().equals(timeTableRequestDto.getBatchId())) {
    Batch newBatch = batchRepository.findById(timeTableRequestDto.getBatchId())
            .orElseThrow(() -> new EntityNotFoundException("Batch not found"));
    timeTable.setBatch(newBatch);
}

// Check if courseTeacher changed
if (!timeTable.getCourseTeacher().getCourseTeacherId().equals(timeTableRequestDto.getCourseteacherId())) {
    CourseTeacher newCT = courseTeacherRepository.findById(timeTableRequestDto.getCourseteacherId())
            .orElseThrow(() -> new EntityNotFoundException("CourseTeacher not found"));
    timeTable.setCourseTeacher(newCT);
}


         boolean dayChanged = !timeTable.getDayOfWeek().equals(timeTableRequestDto.getDayOfWeek());
        boolean startChanged = !timeTable.getStartTime().equals(timeTableRequestDto.getStartTime());
        boolean endChanged = !timeTable.getEndTime().equals(timeTableRequestDto.getEndTime());

        if (dayChanged || startChanged || endChanged) {
            boolean conflict = timeTableRepository.existsByBatchAndDayOfWeekAndStartTimeLessThanAndEndTimeGreaterThanAndTimetableIdNot(
                    timeTable.getBatch(),
              timeTableRequestDto.getDayOfWeek(),
            timeTableRequestDto.getStartTime(),
            timeTableRequestDto.getEndTime(),
                    timetableId
            );
            if (conflict) {
                throw new IllegalArgumentException("Time slot overlaps with an existing timetable for this batch");
            }
        }

        timeTableMapper.update(timeTableRequestDto, timeTable);
        return timeTableMapper.toResponseDto(timeTable);        
        
    }

    @Override
    @Transactional
    public void deleteTimeTable(Long timetableId) {
        TimeTable timeTable=timeTableRepository.findById(timetableId)
                .orElseThrow(() -> new EntityNotFoundException("TimeTable not found"));
        timeTable.setDeleted(true);
    }
}

