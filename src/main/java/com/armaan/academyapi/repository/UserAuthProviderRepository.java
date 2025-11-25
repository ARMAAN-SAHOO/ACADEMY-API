package com.armaan.academyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.AuthProvider;
import com.armaan.academyapi.entity.UserAuthProvider;

public interface UserAuthProviderRepository extends JpaRepository<UserAuthProvider, Long> {

    Optional<UserAuthProvider> findByProviderAndProviderUserId(AuthProvider provider, String providerUserId);

    boolean existsByProviderAndProviderUserId(AuthProvider provider, String providerUserId);
}
