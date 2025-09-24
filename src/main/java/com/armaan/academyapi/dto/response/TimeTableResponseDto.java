package com.armaan.academyapi.dto.response;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeTableResponseDto {

    private Long timetableId;  
    private Long batchId;
    private Long courseteacherId;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;  // e.g., "10:00"
    private LocalTime endTime;
}