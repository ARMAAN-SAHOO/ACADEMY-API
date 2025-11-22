package com.armaan.academyapi.dto.response;

import java.time.LocalDate;

import com.armaan.academyapi.enums.EnrollmentStatus;

import lombok.Getter;

@Getter
public class EnrollmentResponseDto {

    private Long enrollmentId;
    private LocalDate enrolledOn;
    private EnrollmentStatus status;
}