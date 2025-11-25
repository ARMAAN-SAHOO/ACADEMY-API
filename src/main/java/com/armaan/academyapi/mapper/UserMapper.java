package com.armaan.academyapi.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.UserRequestDto;
import com.armaan.academyapi.dto.response.UserResponseDto;
import com.armaan.academyapi.dto.update.UserUpdateDto;
import com.armaan.academyapi.entity.User;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
       /*  uses = {CandidateProfileMapper.class, RecruiterProfileMapper.class}*/) // 👈 delegation
        
public interface UserMapper {
    User toEntity(UserRequestDto dto);
    UserResponseDto toResponseDto(User user);
    void update(UserUpdateDto dto, @MappingTarget User user);
}
