package com.armaan.academyapi.dto.response;

import java.time.LocalDate;

import com.armaan.academyapi.enums.Gender;
import com.armaan.academyapi.enums.Role;
import lombok.Getter;


@Getter
public class UserResponseDto {

    private Long userId;
    private String email;
    private String userName;
    private String phoneNumber;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String address;
    private Role role;
}