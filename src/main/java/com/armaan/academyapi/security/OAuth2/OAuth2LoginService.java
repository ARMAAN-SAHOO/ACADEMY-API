package com.armaan.academyapi.security.OAuth2;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armaan.academyapi.dto.response.OAuthUserInfo;
import com.armaan.academyapi.dto.response.TokensResponse;
import com.armaan.academyapi.entity.User;
import com.armaan.academyapi.security.AuthService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final Map<String, OAuth2Service> providers; // GOOGLE → service
    private final AuthService authService;              // handles user linking + tokens
    private final GoogleMobileOAuthService googleMobileOAuthService;
    private static final Logger logger = LoggerFactory.getLogger(OAuth2LoginService.class);

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

        // ------------------------------
    // NEW METHOD for Android login (ID token)
    // ------------------------------
    @Transactional
    public TokensResponse loginWithIdToken(String idToken) throws Exception {
        // 1️⃣ Verify the Android ID token
        OAuthUserInfo info = googleMobileOAuthService.verifyIdToken(idToken);

        logger.info("OAuth provider string from info: {}", info.getProvider());
        // 2️⃣ Link or register the user in the backend
        User user = authService.loginOrRegister(info);

        // 3️⃣ Generate access + refresh tokens
        return authService.generateTokensForUser(user);
    }

}

