package com.armaan.academyapi.dto.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultUpdateDto {

    private Long examId;
    private Long studentId;
    private Integer marksObtanined;
}