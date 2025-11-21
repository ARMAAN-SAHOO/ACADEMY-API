package com.armaan.academyapi.security;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.LoginRequestDto;
import com.armaan.academyapi.dto.request.UserRequestDto;
import com.armaan.academyapi.dto.response.OAuthUserInfo;
import com.armaan.academyapi.dto.response.UserResponseDto;
import com.armaan.academyapi.entity.RefreshToken;
import com.armaan.academyapi.entity.User;
import com.armaan.academyapi.entity.UserAuthProvider;
import com.armaan.academyapi.enums.Role;
import com.armaan.academyapi.mapper.UserMapper;
import com.armaan.academyapi.repository.RefreshTokenRepository;
import com.armaan.academyapi.repository.UserAuthProviderRepository;
import com.armaan.academyapi.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserAuthProviderRepository authProviderRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    // ----------------------------------------------------
    // LOCAL REGISTER
    // ----------------------------------------------------
    public UserResponseDto signUp(UserRequestDto userDto) {

    if (userRepository.existsByEmail(userDto.getEmail())) {
        throw new RuntimeException("User already exists");
    }

    User user = userMapper.toEntity(userDto);
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setLocalAccountEnabled(true);  // <--- NEW
    user.setPasswordSet(true);          // <--- NEW

    UserAuthProvider userAuthProvider = new UserAuthProvider();
    userAuthProvider.setProvider("LOCAL");
    userAuthProvider.setProviderUserId(user.getEmail());
    userAuthProvider.setUser(user);

    user.getAuthProviders().add(userAuthProvider);
    
    authProviderRepository.save(userAuthProvider);
    userRepository.save(user);

    return userMapper.toResponseDto(user);
}


    // ----------------------------------------------------
    // LOCAL LOGIN
    // ----------------------------------------------------
    public TokensResponse signIn(LoginRequestDto userDto) throws AuthenticationException {

    User user = userRepository.findByEmail(userDto.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.isLocalAccountEnabled()) {
        String provider = user.getAuthProviders().stream()
                .map(UserAuthProvider::getProvider)
                .filter(p -> !"LOCAL".equals(p))
                .findFirst()
                .orElse("OAuth");

        throw new RuntimeException(
                "This account was created using " + provider +
                ". Please login with " + provider + " or add a password in account settings."
        );
    }

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    userDto.getEmail(), userDto.getPassword()
            )
    );

    return generateTokensForUser(user);
}


    // ----------------------------------------------------
    // USED BY OAUTH2
    // Common reusable method for both Google & Local login
    // ----------------------------------------------------
    public TokensResponse generateTokensForUser(User user) {

        List<String> roles = List.of("ROLE_" + user.getRole().name());

        // Access token
        String accessToken = jwtUtil.generateAccessToken(user.getEmail(), roles);

        // Refresh token
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

    // ----------------------------------------------------
    // OAUTH2 LOGIN/REGISTER (Google, GitHub, etc.)
    // ----------------------------------------------------
@Transactional
public User loginOrRegister(OAuthUserInfo info) {

    // CASE 1 — Provider already linked
    Optional<UserAuthProvider> existingProvider =
            authProviderRepository.findByProviderAndProviderUserId(
                    info.getProvider(), info.getProviderUserId()
            );

    if (existingProvider.isPresent()) {
        return existingProvider.get().getUser();
    }

    // CASE 2 — Email exists → link OAuth provider to existing LOCAL user
    Optional<User> existingUser = userRepository.findByEmail(info.getEmail());
    if (existingUser.isPresent()) {
        User user = existingUser.get();

        UserAuthProvider provider = new UserAuthProvider();
        provider.setProvider(info.getProvider());
        provider.setProviderUserId(info.getProviderUserId());
        provider.setUser(user);

        authProviderRepository.save(provider);

        return user;
    }

    // CASE 3 — New OAuth user
    User user = new User();
    user.setEmail(info.getEmail());
    user.setRole(Role.STUDENT);

    user.setLocalAccountEnabled(false); // <--- Important
    user.setPasswordSet(false);         // <--- Important

    userRepository.save(user);

    UserAuthProvider provider = new UserAuthProvider();
    provider.setProvider(info.getProvider());
    provider.setProviderUserId(info.getProviderUserId());
    provider.setUser(user);

    authProviderRepository.save(provider);

    return user;
}

    // ----------------------------------------------------
    // REFRESH TOKEN
    // ----------------------------------------------------
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

        return generateTokensForUser(user);
    }

    // ----------------------------------------------------
    // LOGOUT ALL DEVICES
    // ----------------------------------------------------
    @Transactional
    public void logoutAll(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<RefreshToken> tokens = refreshTokenRepository.findAllByUser(user);
        refreshTokenRepository.deleteAll(tokens);
    }

    // ----------------------------------------------------
    // TOKEN DTO
    // ----------------------------------------------------
    public record TokensResponse(String accessToken, String refreshToken) {}
}
