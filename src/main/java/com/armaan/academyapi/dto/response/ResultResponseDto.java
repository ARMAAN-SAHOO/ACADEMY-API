package com.armaan.academyapi.dto.response;

import lombok.Getter;

@Getter
public class ResultResponseDto {

    private Long resultId;
    private Long examId;
    private Long studentId;
    private Integer marksObtanined;
}