package com.armaan.academyapi.dto.response;

import com.armaan.academyapi.enums.RelationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentResponseDto {

    private Long parentId;
    private String fullName;
    private String contact;
    private RelationType relation;
}