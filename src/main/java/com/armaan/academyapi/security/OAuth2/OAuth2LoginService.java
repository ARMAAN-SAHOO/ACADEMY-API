package com.armaan.academyapi.security.OAuth2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armaan.academyapi.dto.response.OAuthUserInfo;
import com.armaan.academyapi.entity.User;
import com.armaan.academyapi.security.AuthService;
import com.armaan.academyapi.security.AuthService.TokensResponse;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final Map<String, OAuth2Service> providers; // GOOGLE → service
    private final AuthService authService;              // handles user linking + tokens

    @Transactional
    public TokensResponse login(String provider, String code) throws Exception {

        // 1. Find provider implementation
        OAuth2Service service = providers.get(provider.toUpperCase());
        if (service == null) {
            throw new IllegalArgumentException("Unsupported provider: " + provider);
        }

        // 2. Exchange code → fetch user info
        OAuthUserInfo info = service.fetchUserInfo(code);

        // 3. Centralized linking logic in AuthService
        User user = authService.loginOrRegister(info);

        // 4. Generate access & refresh tokens
        return authService.generateTokensForUser(user);
    }
}

