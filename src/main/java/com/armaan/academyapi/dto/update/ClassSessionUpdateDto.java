package com.armaan.academyapi.dto.update;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassSessionUpdateDto {
    private Long timetableId;   // which timetable/course/batch this session belongs to
    private LocalDate date;     // date of the session
    private Integer slotIndex;  // slot of the day
}
