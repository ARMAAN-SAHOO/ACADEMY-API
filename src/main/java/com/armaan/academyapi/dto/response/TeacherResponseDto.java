package com.armaan.academyapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponseDto {

    private Long teacherId;
   // private Long userId;
    private String fullName;
    private String contact;
}