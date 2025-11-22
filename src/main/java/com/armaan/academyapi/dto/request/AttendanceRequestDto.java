package com.armaan.academyapi.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequestDto {

    @NotNull
     private Long enrollmentId;
     @NotNull
    private LocalDate date; 
    @NotNull
    private Boolean presence;
}