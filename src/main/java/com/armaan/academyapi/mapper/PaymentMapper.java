package com.armaan.academyapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.armaan.academyapi.dto.request.PaymentRequestDto;
import com.armaan.academyapi.dto.response.PaymentResponseDto;
import com.armaan.academyapi.entity.Payment;


@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy =ReportingPolicy.IGNORE

)
public interface PaymentMapper extends BaseMapper<Payment,PaymentRequestDto,PaymentResponseDto> {

}
