package com.armaan.academyapi.dto.request;

import com.armaan.academyapi.enums.Grade;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudentRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @NotBlank
    private String schoolName;

    @NotNull
    private ParentRequestDto parentRequestDto;
    
}