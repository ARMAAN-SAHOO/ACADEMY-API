package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.CourseRequestDto;
import com.armaan.academyapi.dto.response.CourseResponseDto;
import com.armaan.academyapi.entity.Course;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface CourseMapper extends BaseMapper<Course,CourseRequestDto,CourseResponseDto>{

}
