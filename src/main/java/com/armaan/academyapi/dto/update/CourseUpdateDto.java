package com.armaan.academyapi.dto.update;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseUpdateDto {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;
}