package com.armaan.academyapi.dto.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentResponseDto {

    private Long studentId;
    private String fullName;
    private String phone;

    //private UserResponseDto user;
    private ParentResponseDto parent;
}
