package com.armaan.academyapi.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClassSessionRequestDto {
    
    @NotNull
    private Long timetableId;   // which timetable/course/batch this session belongs to

    @NotNull
    private LocalDate date;     // date of the session
}
