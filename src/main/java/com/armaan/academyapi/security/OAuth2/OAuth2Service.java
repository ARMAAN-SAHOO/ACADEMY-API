package com.armaan.academyapi.security.OAuth2;

import com.armaan.academyapi.dto.response.OAuthUserInfo;
public interface OAuth2Service {

    /**
     * Exchange the OAuth2 authorization code for user info.
     *
     * @param code Authorization code received from the provider
     * @return User info from provider
     * @throws Exception if anything goes wrong
     */
    OAuthUserInfo fetchUserInfo(String code) throws Exception;
}