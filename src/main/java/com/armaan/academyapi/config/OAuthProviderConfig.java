package com.armaan.academyapi.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.armaan.academyapi.security.OAuth2.GoogleOAuth2Service;
import com.armaan.academyapi.security.OAuth2.OAuth2Service;

@Configuration
public class OAuthProviderConfig {

    @Bean
    public Map<String, OAuth2Service> oAuthProviders(GoogleOAuth2Service googleOAuth2Service) {

        return Map.of(
                "GOOGLE", googleOAuth2Service
        );
    }
}
