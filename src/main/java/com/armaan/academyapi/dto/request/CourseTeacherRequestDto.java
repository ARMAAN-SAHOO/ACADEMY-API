package com.armaan.academyapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseTeacherRequestDto {
    private Long courseId;
    private Long teacherId;
}
