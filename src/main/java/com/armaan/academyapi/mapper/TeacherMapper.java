package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.TeacherRequestDto;
import com.armaan.academyapi.dto.response.TeacherResponseDto;
import com.armaan.academyapi.entity.Teacher;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface TeacherMapper extends BaseMapper<Teacher,TeacherRequestDto,TeacherResponseDto>{

}
