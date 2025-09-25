package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.AttendanceRequestDto;
import com.armaan.academyapi.dto.response.AttendanceResponseDto;
import com.armaan.academyapi.dto.update.AttendanceUpdateDto;
import com.armaan.academyapi.entity.Attendance;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface AttendanceMapper extends BaseMapper<Attendance,AttendanceRequestDto,AttendanceResponseDto,AttendanceUpdateDto>{

}
