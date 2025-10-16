package com.armaan.academyapi.security.OAuth2;

import com.armaan.academyapi.dto.response.OAuthUserInfo;
import com.armaan.academyapi.dto.response.TokensResponse;

public interface OAuth2Service {

    /**
     * Exchange the OAuth2 authorization code for user info.
     *
     * @param code Authorization code received from the provider
     * @return User info from provider
     * @throws Exception if anything goes wrong
     */
    OAuthUserInfo fetchUserInfo(String code) throws Exception;

    /**
     * Log in or register the user in our system based on the provider info.
     *
     * @param userInfo Provider user info
     * @return JWT access token (and optionally refresh token)
     */
    TokensResponse loginOrRegister(OAuthUserInfo userInfo);
}