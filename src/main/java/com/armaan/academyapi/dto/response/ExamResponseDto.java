package com.armaan.academyapi.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.enums.ExamType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamResponseDto {

    private Long examId;
    private String name;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private Integer totalMarks;
    
    private ExamType type;
    private ExamStatus status;
}