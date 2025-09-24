package com.armaan.academyapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseTeacherResponseDto {

    private Long courseTeacherId;
    private Long courseId;
    private Long teacherId;
}