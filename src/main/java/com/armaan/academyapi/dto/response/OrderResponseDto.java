package com.armaan.academyapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private String id;
    private Integer amount;
    private String currency;
    private String receipt;
    private String status;
}

