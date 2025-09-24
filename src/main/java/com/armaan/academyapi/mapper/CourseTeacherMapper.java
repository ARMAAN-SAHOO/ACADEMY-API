package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.CourseTeacherRequestDto;
import com.armaan.academyapi.dto.response.CourseResponseDto;
import com.armaan.academyapi.entity.CourseTeacher;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface CourseTeacherMapper extends BaseMapper<CourseTeacher,CourseTeacherRequestDto,CourseResponseDto>{

}
