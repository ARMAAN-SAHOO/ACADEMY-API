package com.armaan.academyapi.dto.update;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceUpdateDto {

    
     private Long enrollmentId;
    private LocalDateTime time; 
    private String sessionBitmask;
}