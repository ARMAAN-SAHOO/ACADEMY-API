package com.armaan.academyapi.service;

import com.armaan.academyapi.dto.response.UserResponseDto;
import com.armaan.academyapi.dto.update.UserUpdateDto;

public interface UserService {

    UserResponseDto getUser(Long userId);

    UserResponseDto updateUser(Long userId, UserUpdateDto dto);

    void changePassword(String  email, String oldPassword, String newPassword);

    void softDeleteUser(Long userId);
}

