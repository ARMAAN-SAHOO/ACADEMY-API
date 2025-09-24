package com.armaan.academyapi.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.enums.ExamType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamRequestDto {

    private Long batchId;
    private Long courseId;
    
    private String name;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    
    private Integer totalMarks;
    
    private ExamType type;    // EXAM or CLASS_TEST
    private ExamStatus status; // optional, defaults to SCHEDULED
}