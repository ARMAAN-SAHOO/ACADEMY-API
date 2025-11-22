package com.armaan.academyapi.dto.request;

import com.armaan.academyapi.enums.Qualification;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Qualification qualification;
}