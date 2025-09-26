package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.CourseTeacherRequestDto;
import com.armaan.academyapi.dto.response.CourseTeacherResponseDto;
import com.armaan.academyapi.dto.update.CourseTeacherUpdateDto;
import com.armaan.academyapi.entity.CourseTeacher;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface CourseTeacherMapper extends BaseMapper<CourseTeacher,CourseTeacherRequestDto,CourseTeacherResponseDto,CourseTeacherUpdateDto>{

}
