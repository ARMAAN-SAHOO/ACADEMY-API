package com.armaan.academyapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentRequestDto {

    private Long studentId;
    private Long batchId;
}