package com.armaan.academyapi.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequestDto {

     private Long enrollmentId;
    private LocalDate date; 
    private String sessionBitmask;
}