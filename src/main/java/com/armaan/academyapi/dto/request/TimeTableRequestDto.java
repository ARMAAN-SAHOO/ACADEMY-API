package com.armaan.academyapi.dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeTableRequestDto {

    private Long batchId;
    private Long courseteacherId;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;  // e.g., "10:00"
    private LocalTime endTime;

}