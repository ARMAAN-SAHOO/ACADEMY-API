package com.armaan.academyapi.dto.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseTeacherUpdateDto {
    private Long courseId;
    private Long teacherId;
}
