package com.armaan.academyapi.dto.request;

import java.time.LocalDate;

import com.armaan.academyapi.enums.EnrollmentStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnrollmentRequestDto {

    @NotNull
    private Long studentId;

    @NotNull
    private Long batchId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @NotNull
    private LocalDate paymentDue;
}