package com.armaan.academyapi.security.OAuth2;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.response.OAuthUserInfo;
import com.armaan.academyapi.dto.response.TokensResponse;
import com.armaan.academyapi.entity.RefreshToken;
import com.armaan.academyapi.entity.User;
import com.armaan.academyapi.entity.UserAuthProvider;
import com.armaan.academyapi.enums.Role;
import com.armaan.academyapi.repository.RefreshTokenRepository;
import com.armaan.academyapi.repository.UserAuthProviderRepository;
import com.armaan.academyapi.repository.UserRepository;
import com.armaan.academyapi.security.JwtUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import io.github.cdimascio.dotenv.Dotenv;

@Service
@RequiredArgsConstructor
public class GoogleOAuth2Service implements OAuth2Service {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final UserAuthProviderRepository authProviderRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    private final Dotenv dotenv = Dotenv.load();
    private final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private final String USERINFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo";
    private final String clientId = dotenv.get("CLIENT_ID");
    private final String clientSecret = dotenv.get("CLIENT_SECRET");
    private final String redirectUri = dotenv.get("REDIRECT_URI");

    // ---------------- FETCH USER INFO ----------------
    @Override
    public OAuthUserInfo fetchUserInfo(String code) throws Exception {
        System.out.println("inside fetch user info of GoogleService");
        // Exchange code for access token
        RequestBody requestBody = new FormBody.Builder()
                .add("code", code)
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .add("redirect_uri", redirectUri)
                .add("grant_type", "authorization_code")
                .build();

        Request tokenRequest = new Request.Builder()
                .url(TOKEN_URL)
                .post(requestBody)
                .build();

        String accessToken;
        try (Response response = client.newCall(tokenRequest).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Failed to get token");
            JsonNode tokenJson = objectMapper.readTree(response.body().string());
            System.out.println(tokenJson.toString());
            accessToken = tokenJson.get("access_token").asText();
        }

        // Fetch user info
        Request userInfoRequest = new Request.Builder()
                .url(USERINFO_URL)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(userInfoRequest).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Failed to fetch user info");

            JsonNode userJson = objectMapper.readTree(response.body().string());
                System.out.println(userJson.toString());
            // ✅ Ensure email is verified
            if (!userJson.has("email_verified") || !userJson.get("email_verified").asBoolean()) {
                throw new RuntimeException("Email not verified with Google");
            }

            // Build DTO
            OAuthUserInfo oAuthUserInfo=new OAuthUserInfo();
                    oAuthUserInfo.setProvider("GOOGLE");
                    oAuthUserInfo.setProviderUserId(userJson.get("sub").asText());
                    oAuthUserInfo.setEmail(userJson.get("email").asText().toLowerCase());
                    oAuthUserInfo.setName(userJson.has("name") ? userJson.get("name").asText() : null);
            System.out.println("returning oauthuseringo");
            return oAuthUserInfo;
        }
    }

    // ---------------- LOGIN OR REGISTER ----------------
    @Override
    public TokensResponse loginOrRegister(OAuthUserInfo userInfo) {

        System.out.println("inside login or register");
        Optional<UserAuthProvider> optionalAuth = authProviderRepository
                .findByProviderAndProviderUserId(userInfo.getProvider(), userInfo.getProviderUserId());

        User user;
        if (optionalAuth.isPresent()) {
            // User exists
            user = optionalAuth.get().getUser();
        } else {
            // Register new user
            user = new User();
            user.setEmail(userInfo.getEmail());
            user.setRole(Role.STUDENT); // default
            userRepository.save(user);

            UserAuthProvider authProvider = new UserAuthProvider();
            authProvider.setProvider(userInfo.getProvider());
            authProvider.setProviderUserId(userInfo.getProviderUserId());
            authProvider.setUser(user);
            authProviderRepository.save(authProvider);
        }

        // Generate JWT
        List<String> roles = List.of("ROLE_" + user.getRole().name());
         String accessToken = jwtUtil.generateAccessToken(user.getEmail(), roles);

    // 4️⃣ Generate / fetch Refresh Token
    RefreshToken refreshToken = refreshTokenRepository.findByUser(user)
            .filter(token -> token.getExpiryDate().isAfter(Instant.now()))
            .orElseGet(() -> {
                String tokenStr = jwtUtil.generateRefreshToken(user.getEmail());
                RefreshToken newToken = RefreshToken.builder()
                        .token(tokenStr)
                        .expiryDate(Instant.now().plusMillis(jwtUtil.getRefreshExpiration()))
                        .user(user)
                        .build();
                return refreshTokenRepository.save(newToken);
            });

        System.out.println("returning tokens");
    // 5️⃣ Return both tokens
    return new TokensResponse(accessToken, refreshToken.getToken());
    }
}

/*
 * Token	Purpose	Who validates it	Expiry
Google access token	Lets you call Google APIs (email, profile, Drive, etc.)	Google	Short-lived (1 hour)
Google refresh token	Lets you get a new Google access token without asking user again	Google	Long-lived, can be revoked by user
Your JWT access token	Lets user access your backend	Your backend	Configurable (minutes/hours)
Your refresh token	Lets you get a new your JWT access token
 */