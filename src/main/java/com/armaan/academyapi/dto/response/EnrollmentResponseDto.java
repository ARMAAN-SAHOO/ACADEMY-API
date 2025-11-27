package com.armaan.academyapi.dto.response;

import java.time.LocalDate;

import com.armaan.academyapi.enums.EnrollmentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentResponseDto {

    private Long enrollmentId;
    private LocalDate enrolledOn;
    private EnrollmentStatus status;
}