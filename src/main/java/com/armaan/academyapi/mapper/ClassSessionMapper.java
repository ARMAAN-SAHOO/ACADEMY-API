package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.ClassSessionRequestDto;
import com.armaan.academyapi.dto.response.ClassSessionResponseDto;
import com.armaan.academyapi.dto.update.ClassSessionUpdateDto;
import com.armaan.academyapi.entity.ClassSession;


@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface ClassSessionMapper extends BaseMapper<ClassSession,ClassSessionRequestDto,ClassSessionResponseDto,ClassSessionUpdateDto>{

}
