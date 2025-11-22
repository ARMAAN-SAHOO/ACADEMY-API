package com.armaan.academyapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseTeacherRequestDto {

    @NotNull
    private Long courseId;
    @NotNull
    private Long teacherId;
}
