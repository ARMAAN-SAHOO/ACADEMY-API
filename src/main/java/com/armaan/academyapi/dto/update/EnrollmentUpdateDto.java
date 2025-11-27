package com.armaan.academyapi.dto.update;

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
public class EnrollmentUpdateDto {


    @NotNull
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @NotNull
    private LocalDate paymentDue;
}