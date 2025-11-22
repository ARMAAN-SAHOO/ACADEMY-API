package com.armaan.academyapi.dto.response;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ClassSessionResponseDto {
    private Long sessionId;
    private Long timetableId;
    private LocalDate date;
    private Integer slotIndex;
}
