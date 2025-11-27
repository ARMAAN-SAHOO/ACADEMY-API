package com.armaan.academyapi.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassSessionResponseDto {
    private Long sessionId;
    private Long timetableId;
    private LocalDate date;
    private Integer slotIndex;
}
