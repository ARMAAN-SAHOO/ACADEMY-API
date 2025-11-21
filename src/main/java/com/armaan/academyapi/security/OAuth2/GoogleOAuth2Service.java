package com.armaan.academyapi.security.OAuth2;

import lombok.RequiredArgsConstructor;
import okhttp3.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.armaan.academyapi.dto.response.OAuthUserInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class GoogleOAuth2Service implements OAuth2Service {

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    private final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private final String USERINFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo";
@Value("${google.client-id}")
private String clientId;

@Value("${google.client-secret}")
private String clientSecret;

@Value("${google.redirect-uri}")
private String redirectUri;

    // ---------------- FETCH USER INFO ----------------
    @Override
    public OAuthUserInfo fetchUserInfo(String code) throws Exception {
        System.out.println("inside fetch user info of GoogleService");

        // 1️⃣ Exchange code for access token
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
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to get token from Google");
            }
            JsonNode tokenJson = objectMapper.readTree(response.body().string());
            System.out.println(tokenJson.toString());
            accessToken = tokenJson.get("access_token").asText();
        }

        // 2️⃣ Fetch user info
        Request userInfoRequest = new Request.Builder()
                .url(USERINFO_URL)
                .header("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(userInfoRequest).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to fetch user info");
            }

            JsonNode userJson = objectMapper.readTree(response.body().string());
            System.out.println(userJson.toString());

            // Ensure email is verified
            if (!userJson.has("email_verified") || !userJson.get("email_verified").asBoolean()) {
                throw new RuntimeException("Email not verified with Google");
            }

            // Build DTO
            OAuthUserInfo oAuthUserInfo = new OAuthUserInfo();
            oAuthUserInfo.setProvider("GOOGLE");
            oAuthUserInfo.setProviderUserId(userJson.get("sub").asText());
            oAuthUserInfo.setEmail(userJson.get("email").asText().toLowerCase());
            oAuthUserInfo.setName(userJson.has("name") ? userJson.get("name").asText() : null);

            return oAuthUserInfo;
        }
    }
}