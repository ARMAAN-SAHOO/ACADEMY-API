package com.armaan.academyapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponseDto {

    private Long resultId;
    private Long examId;
    private Long studentId;
    private Integer marksObtanined;
}