package com.armaan.academyapi.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BatchUpdateDto {

    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Min(value = 0, message = "Fees must be non-negative")
    private Integer fees;
}
