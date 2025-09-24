package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.ExamRequestDto;
import com.armaan.academyapi.dto.response.ExamResponseDto;
import com.armaan.academyapi.entity.Exam;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface ExamMapper extends BaseMapper<Exam,ExamRequestDto,ExamResponseDto>{

}
