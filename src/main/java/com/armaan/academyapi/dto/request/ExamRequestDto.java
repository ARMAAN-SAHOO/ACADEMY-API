package com.armaan.academyapi.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.enums.ExamType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExamRequestDto {

    @NotNull
    private Long batchId;
    @NotNull
    private Long courseId;
    
    @NotBlank
    private String name;

    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    
    @NotNull
    private Integer totalMarks;
    @NotNull
    private ExamType type;    // EXAM or CLASS_TEST
    @NotNull
    private ExamStatus status; // optional, defaults to SCHEDULED
}