package com.armaan.academyapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthProvider {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAuthProviderId;

    /**
     * Provider type: LOCAL, GOOGLE, GITHUB, etc.
     */
    @Column(nullable = false)
    private String provider;

    /**
     * Unique ID from provider
     * - LOCAL: can be email
     * - GOOGLE: sub claim
     * - GITHUB: github user id
     */
    @Column(nullable = false, unique = true)
    private String providerUserId;

    /**
     * Link back to the main User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
