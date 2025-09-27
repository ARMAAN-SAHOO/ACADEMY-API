package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.ParentRequestDto;
import com.armaan.academyapi.dto.response.ParentResponseDto;

public interface ParentService {

    ParentResponseDto createParent(ParentRequestDto parentRequestDto);
    ParentResponseDto getParentById(Long parentId);
    List<ParentResponseDto> getAllParents();
    void deleteParent(Long parentId);
}


