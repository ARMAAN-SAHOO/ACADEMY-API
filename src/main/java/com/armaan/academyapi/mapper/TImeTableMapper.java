package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.TimeTableRequestDto;
import com.armaan.academyapi.dto.response.TimeTableResponseDto;
import com.armaan.academyapi.dto.update.TimeTableUpdateDto;
import com.armaan.academyapi.entity.TimeTable;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface TimeTableMapper extends BaseMapper<TimeTable,TimeTableRequestDto,TimeTableResponseDto,TimeTableUpdateDto>{

}
