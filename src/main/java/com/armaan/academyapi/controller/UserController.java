package com.armaan.academyapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.dto.response.UserResponseDto;
import com.armaan.academyapi.dto.update.UserUpdateDto;
import com.armaan.academyapi.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {

        UserResponseDto userResponseDto=userService.updateUser(id,userUpdateDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser( @PathVariable Long id) {
        UserResponseDto userResponseDto=userService.getUser(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id")
    public ResponseEntity<UserResponseDto> deleteUser( @PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
