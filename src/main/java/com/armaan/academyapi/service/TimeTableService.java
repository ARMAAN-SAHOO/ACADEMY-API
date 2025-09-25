package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.TimeTableRequestDto;
import com.armaan.academyapi.dto.response.TimeTableResponseDto;
import com.armaan.academyapi.dto.update.TimeTableUpdateDto;

public interface TimeTableService {
    TimeTableResponseDto createTimeTable(TimeTableRequestDto timetableRequestDto);
    TimeTableResponseDto updateTimeTable(Long timetableId,TimeTableUpdateDto timeTableUpdateDto);
    TimeTableResponseDto getTimeTableById(Long timetableId);
    List<TimeTableResponseDto> getTimeTablesForBatch(Long batchId);
    void deleteTimeTable(Long timetableId);
}

