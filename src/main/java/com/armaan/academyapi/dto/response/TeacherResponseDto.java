package com.armaan.academyapi.dto.response;

import com.armaan.academyapi.enums.Qualification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponseDto {

    private Long teacherId;
    private Qualification qualification;
}