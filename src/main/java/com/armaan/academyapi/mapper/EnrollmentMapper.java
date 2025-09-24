package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.EnrollmentRequestDto;
import com.armaan.academyapi.dto.response.EnrollmentResponseDto;
import com.armaan.academyapi.entity.Enrollment;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface EnrollmentMapper extends BaseMapper<Enrollment,EnrollmentRequestDto,EnrollmentResponseDto>{

}
