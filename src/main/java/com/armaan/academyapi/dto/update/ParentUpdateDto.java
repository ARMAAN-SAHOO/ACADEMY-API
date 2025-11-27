package com.armaan.academyapi.dto.update;

import com.armaan.academyapi.enums.RelationType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParentUpdateDto {


    @NotNull
    @Size(max = 100)
    private String fullName;

    @NotNull
    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    private String contact;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RelationType relation;
}