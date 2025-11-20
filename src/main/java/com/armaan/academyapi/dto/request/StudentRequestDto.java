package com.armaan.academyapi.dto.request;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequestDto {

    private String fullName;
    private String phone;
    private Long userId;
    private ParentRequestDto parentRequestDto;
    
}