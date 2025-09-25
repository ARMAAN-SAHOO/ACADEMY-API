package com.armaan.academyapi.dto.update;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeTableUpdateDto {

    private Long batchId;
    private Long courseteacherId;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;  // e.g., "10:00"
    private LocalTime endTime;

}