package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.StudentRequestDto;
import com.armaan.academyapi.dto.response.StudentResponseDto;
import com.armaan.academyapi.dto.update.StudentUpdateDto;
import com.armaan.academyapi.entity.Student;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE/*,
    uses = {UserMapper.class,ParentMapper.class}*/

)
public interface StudentMapper extends BaseMapper<Student,StudentRequestDto,StudentResponseDto,StudentUpdateDto>{

}
