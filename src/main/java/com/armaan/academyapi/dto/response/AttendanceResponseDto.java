package com.armaan.academyapi.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class AttendanceResponseDto {

    private Long attendanceId;
    private Long enrollmentId;        
    private LocalDate date;
    private String sessionBitmask;    // "1011"
    private Integer totalSessions;    // calculated from timetable
    
}