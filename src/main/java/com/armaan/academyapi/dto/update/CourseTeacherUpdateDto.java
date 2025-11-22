package com.armaan.academyapi.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseTeacherUpdateDto {

    @NotNull
    private Long courseId;
    @NotNull
    private Long teacherId;
}
