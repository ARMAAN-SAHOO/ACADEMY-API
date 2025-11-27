package com.armaan.academyapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderResponseDto {
    private String id;
    private Integer amount;
    private String currency;
    private String receipt;
    private String status;
}


