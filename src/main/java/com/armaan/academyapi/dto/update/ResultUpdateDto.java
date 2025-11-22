package com.armaan.academyapi.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultUpdateDto {

    @NotNull
    private Long examId;
    @NotNull
    private Long studentId;
    @NotBlank
    private Integer marksObtanined;
}