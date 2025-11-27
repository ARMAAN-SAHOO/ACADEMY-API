package com.armaan.academyapi.dto.response;

import com.armaan.academyapi.enums.Grade;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentResponseDto {

     private Long studentId;
    private Grade grade;
    private String schoolName;

    private ParentResponseDto parent;
}
