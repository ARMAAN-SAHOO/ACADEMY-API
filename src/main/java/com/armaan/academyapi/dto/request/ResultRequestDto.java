package com.armaan.academyapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultRequestDto {

    @NotNull
    private Long examId;
    @NotNull
    private Long studentId;
    @NotBlank
    private Integer marksObtanined;
}