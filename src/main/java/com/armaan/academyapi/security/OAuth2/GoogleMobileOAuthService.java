package com.armaan.academyapi.security.OAuth2;

import com.armaan.academyapi.dto.response.OAuthUserInfo;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleMobileOAuthService {

    @Value("${google.web-client-id-mobile}")
    private String mobileWebClientId;

    private final NetHttpTransport transport = new NetHttpTransport();
    private final JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

    public OAuthUserInfo verifyIdToken(String idTokenString) throws Exception {

        System.out.println("Received ID token: " + idTokenString);
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(mobileWebClientId))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken == null) {
            throw new RuntimeException("Invalid Google ID token");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        System.out.println("Verified token: " + payload);
        OAuthUserInfo info = new OAuthUserInfo();
        info.setProvider("GOOGLE");
        info.setProviderUserId(payload.getSubject());
        info.setEmail(payload.getEmail());
        info.setName((String) payload.get("name"));

        return info;
    }
}
