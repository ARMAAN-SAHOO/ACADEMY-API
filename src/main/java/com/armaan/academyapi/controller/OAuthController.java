package com.armaan.academyapi.controller;

import com.armaan.academyapi.dto.response.TokensResponse;
import com.armaan.academyapi.security.OAuth2.OAuth2LoginService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/oauth-login")
@RequiredArgsConstructor
public class OAuthController {
    
    private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);
    private final OAuth2LoginService oAuth2LoginService;

    // ------------------------------
    // Existing Web OAuth login (authorization code flow)
    // ------------------------------
    @PostMapping("/{provider}")
    public TokensResponse oauthLogin(@PathVariable String provider,
                                     @RequestBody Map<String, String> body) throws Exception {
        String code = body.get("code");
        if (code == null) {
            throw new IllegalArgumentException("Authorization code is missing");
        }
        return oAuth2LoginService.login(provider, code);
    }

    // ------------------------------
    // New Android Google login (ID token flow)
    // ------------------------------
    @PostMapping("/google-mobile")
    public TokensResponse googleMobileLogin(@RequestBody Map<String, String> body) throws Exception {

        logger.info("got something in controller");
        logger.info(body.toString());
        String idToken = body.get("idToken");
        if (idToken == null) {
            throw new IllegalArgumentException("ID token is missing");
        }
        return oAuth2LoginService.loginWithIdToken(idToken);
    }
}
