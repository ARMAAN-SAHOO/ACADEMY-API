package com.armaan.academyapi.dto.response;

import com.armaan.academyapi.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String email;
    private String userName;
    private String phoneNumber;
    private Role role;
}