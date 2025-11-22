package com.armaan.academyapi.dto.update;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeTableUpdateDto {

    @NotNull
    private Long timeTableId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

     @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

}