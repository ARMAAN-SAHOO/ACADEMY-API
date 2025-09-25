package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.TimeTableRequestDto;
import com.armaan.academyapi.dto.response.TimeTableResponseDto;

public interface TimeTableService {
    TimeTableResponseDto createTimeTable(TimeTableRequestDto timetableRequestDto);
    TimeTableResponseDto updateTimeTable(Long timetableId,TimeTableRequestDto timeTableRequestDto);
    TimeTableResponseDto getTimeTableById(Long timetableId);
    List<TimeTableResponseDto> getTimeTablesForBatch(Long batchId);
    void deleteTimeTable(Long timetableId);
}

