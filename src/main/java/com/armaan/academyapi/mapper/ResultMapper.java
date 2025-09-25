package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.ResultRequestDto;
import com.armaan.academyapi.dto.response.ResultResponseDto;
import com.armaan.academyapi.dto.update.ResultUpdateDto;
import com.armaan.academyapi.entity.Result;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface ResultMapper extends BaseMapper<Result,ResultRequestDto,ResultResponseDto,ResultUpdateDto>{

}
