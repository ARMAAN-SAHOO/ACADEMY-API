package com.armaan.academyapi.dto.update;

import java.time.LocalDate;

import com.armaan.academyapi.enums.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDto {

    @NotNull
    private Long userId;

    @Size(max = 50)
    private String userName;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private String phoneNumber;

     @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank
    private LocalDate dateOfBirth;

    private String address;

}