package com.armaan.academyapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentResponseDto {

    private Long parentId;
    private String fullName;
    private String contact;
    private String relation;
}