package com.armaan.academyapi.mapper;

import com.armaan.academyapi.entity.Batch;
import com.armaan.academyapi.dto.request.;
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface BatchMapper extends BaseMapper<Batch,BatchResponseDto>{

}
