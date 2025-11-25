package com.armaan.academyapi.service.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.armaan.academyapi.dto.response.UserResponseDto;
import com.armaan.academyapi.dto.update.UserUpdateDto;
import com.armaan.academyapi.entity.User;
import com.armaan.academyapi.mapper.UserMapper;
import com.armaan.academyapi.repository.UserRepository;
import com.armaan.academyapi.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto getUser(Long userId) {
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserUpdateDto dto) {

         User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.update(dto, user);

        return userMapper.toResponseDto(user);   
    }

    @Transactional
    public void changePassword(String email, String oldPassword, String newPassword) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // CASE 1 CHECK
    if (user.getPassword()==null) {
        throw new RuntimeException("No password found. Use /set-password instead.");
    }

    // Validate old password
    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
        throw new RuntimeException("Old password is incorrect");
    }

    // Set new password
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
}

    public void setPassword(String email, String newPassword) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Only allow if user has NO password
    if (user.getPassword()!=null) {
        throw new RuntimeException("Password already set. Use change-password instead.");
    }
    user.setPassword(passwordEncoder.encode(newPassword));
    user.setLocalAccountEnabled(true); // enable local login now
    user.setPasswordSet(true);

    userRepository.save(user);
}

    @Override
    public void softDeleteUser(Long userId) {
                User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setDeleted(true);
    }


}
