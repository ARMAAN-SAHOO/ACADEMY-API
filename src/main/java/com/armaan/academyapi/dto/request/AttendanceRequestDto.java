package com.armaan.academyapi.dto.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequestDto {

     private Long enrollmentId;
    private LocalDateTime time; 
    private String sessionBitmask;
}