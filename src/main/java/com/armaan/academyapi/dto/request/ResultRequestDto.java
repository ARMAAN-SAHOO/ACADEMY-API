package com.armaan.academyapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultRequestDto {

    private Long examId;
    private Long studentId;
    private Integer marksObtanined;
}