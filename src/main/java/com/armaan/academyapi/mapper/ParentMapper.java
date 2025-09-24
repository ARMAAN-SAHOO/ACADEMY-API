package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.ParentRequestDto;
import com.armaan.academyapi.dto.response.ParentResponseDto;
import com.armaan.academyapi.entity.Parent;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface ParentMapper extends BaseMapper<Parent,ParentRequestDto,ParentResponseDto>{

}
