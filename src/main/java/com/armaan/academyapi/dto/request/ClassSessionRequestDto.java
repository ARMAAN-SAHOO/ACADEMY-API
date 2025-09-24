package com.armaan.academyapi.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassSessionRequestDto {
    private Long timetableId;   // which timetable/course/batch this session belongs to
    private LocalDate date;     // date of the session
    private Integer slotIndex;  // slot of the day
}
