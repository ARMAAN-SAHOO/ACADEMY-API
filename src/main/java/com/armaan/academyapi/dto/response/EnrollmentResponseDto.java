package com.armaan.academyapi.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentResponseDto {

    private Long enrollmentId;
    private Long studentId;
    private Long batchId;
    private LocalDate enrolledOn;
}