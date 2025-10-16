package com.armaan.academyapi.security;

import java.time.Instant;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.LoginRequestDto;
import com.armaan.academyapi.dto.request.UserRequestDto;
import com.armaan.academyapi.dto.response.UserResponseDto;
import com.armaan.academyapi.entity.RefreshToken;
import com.armaan.academyapi.entity.User;
import com.armaan.academyapi.entity.UserAuthProvider;
import com.armaan.academyapi.mapper.UserMapper;
import com.armaan.academyapi.repository.RefreshTokenRepository;
import com.armaan.academyapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    // ---------------- SIGN UP ----------------
    public UserResponseDto signUp(UserRequestDto userDto) {

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserAuthProvider userAuthProvider=new UserAuthProvider();
        userAuthProvider.setProvider("LOCAL");
        userAuthProvider.setProviderUserId(user.getEmail());
        userAuthProvider.setUser(user);

        if (userAuthProvider.getProvider().equals("LOCAL") &&
    (userAuthProvider.getUser().getPassword() == null || userAuthProvider.getUser().getPassword().isBlank())) {
    throw new RuntimeException("Password required for LOCAL login");
}

        user.getAuthProviders().add(userAuthProvider);
        userRepository.save(user);

        return userMapper.toResponseDto(user);
    }

    // ---------------- SIGN IN ----------------
    public TokensResponse signIn(LoginRequestDto userDto) throws AuthenticationException {

        // Authenticate user
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
        );

        // Load user entity
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert Role enum to String with ROLE_ prefix
        List<String> roles = List.of("ROLE_" + user.getRole().name());

        // Generate access token
        String accessToken = jwtUtil.generateAccessToken(user.getEmail(), roles);

        // Handle refresh token
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user)
                .filter(token -> token.getExpiryDate().isAfter(Instant.now()))
                .orElseGet(() -> {
                    String tokenString = jwtUtil.generateRefreshToken(user.getEmail());
                    RefreshToken newToken = RefreshToken.builder()
                            .token(tokenString)
                            .expiryDate(Instant.now().plusMillis(jwtUtil.getRefreshExpiration()))
                            .user(user)
                            .build();
                    return refreshTokenRepository.save(newToken);
                });

        return new TokensResponse(accessToken, refreshToken.getToken());
    }

    // ---------------- REFRESH TOKEN ----------------
    public TokensResponse refreshToken(String refreshTokenStr) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenStr)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        String username = jwtUtil.extractUsername(refreshTokenStr);
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert role to String list
        List<String> roles = List.of("ROLE_" + user.getRole().name());
        String newAccessToken = jwtUtil.generateAccessToken(username, roles);

        return new TokensResponse(newAccessToken, refreshTokenStr); // refresh token remains the same
    }

    // ---------------- HELPER DTO ----------------
    public record TokensResponse(String accessToken, String refreshToken) {}
}

