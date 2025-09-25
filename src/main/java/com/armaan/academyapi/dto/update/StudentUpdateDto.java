package com.armaan.academyapi.dto.update;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentUpdateDto {

    private String fullName;
    private String phone;
    //private Long userId;   // link to existing user
    private Long parentId; // optional


}