package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.BatchRequestDto;
import com.armaan.academyapi.dto.response.BatchResponseDto;
import com.armaan.academyapi.dto.update.BatchUpdateDto;
import com.armaan.academyapi.entity.Batch;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface BatchMapper extends BaseMapper<Batch,BatchRequestDto,BatchResponseDto,BatchUpdateDto> {

    
}