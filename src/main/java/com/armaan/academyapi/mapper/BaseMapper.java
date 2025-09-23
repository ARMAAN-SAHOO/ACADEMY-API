package com.armaan.academyapi.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper<E,REQ,RES> {

        E toEntity(REQ dto);

    RES toResponseDto(E entity);

    void update(REQ req,@MappingTarget E e);

}
