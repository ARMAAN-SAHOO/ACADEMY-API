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
    private LocalTime startTime;
    private LocalTime endTime;
}