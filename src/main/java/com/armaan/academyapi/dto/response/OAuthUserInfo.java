package com.armaan.academyapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Generic user info returned by OAuth providers.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OAuthUserInfo {

    /**
     * Unique ID from the provider (Google sub, GitHub id, etc.)
     */
    private String providerUserId;

    /**
     * Email of the user
     */
    private String email;

    /**
     * Full name of the user
     */
    private String name;

    /**
     * Profile picture URL if available
     */
    private String pictureUrl;

    /**
     * Provider type (GOOGLE, GITHUB, etc.)
     */
    private String provider;
}
