package com.armaan.academyapi.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper<E,REQ,RES,UPD> {

    E toEntity(REQ dto);

    RES toResponseDto(E entity);

    void update(UPD upd,@MappingTarget E e);

}
