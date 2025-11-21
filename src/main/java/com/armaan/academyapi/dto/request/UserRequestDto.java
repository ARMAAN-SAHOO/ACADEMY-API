package com.armaan.academyapi.dto.request;

import com.armaan.academyapi.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {


    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private Role role;

}