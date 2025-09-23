package com.armaan.academyapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponseDto {

    private Long courseId;
    private String name;
    private String description;
}