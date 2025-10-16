package com.armaan.academyapi.security.OAuth2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.armaan.academyapi.dto.response.OAuthUserInfo;
import com.armaan.academyapi.entity.User;
import com.armaan.academyapi.entity.UserAuthProvider;
import com.armaan.academyapi.enums.Role;
import com.armaan.academyapi.repository.UserAuthProviderRepository;
import com.armaan.academyapi.repository.UserRepository;
import com.armaan.academyapi.security.JwtUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2LoginService {

    private final UserRepository userRepository;
    private final UserAuthProviderRepository userAuthProviderRepository;
    private final JwtUtil jwtUtil;

    private final GoogleOAuth2Service googleOAuth2Service;
    // Add other providers here in future (GitHubOAuth2Service, etc.)

    /**
     * Main entry point for OAuth login.
     * @param provider  GOOGLE, GITHUB, etc.
     * @param code      OAuth authorization code from frontend
     * @return JWT access token
     */
    @Transactional
    public String login(String provider, String code) throws Exception {

        System.out.println("inside login of auth2loginservice");

        OAuthUserInfo userInfo;

        // Determine provider
        switch (provider.toUpperCase()) {
            case "GOOGLE":
                userInfo = googleOAuth2Service.fetchUserInfo(code);
                System.out.println("got user info from the other side");
                break;
            // case "GITHUB": userInfo = githubOAuth2Service.fetchUserInfo(code); break;
            default:
                throw new IllegalArgumentException("Unsupported provider: " + provider);
        }

        System.out.println("calling loginorrefister of auth2lofinservice");

        // Login or register user
        return loginOrRegister(userInfo);
    }

    /**
     * Checks if a user already exists for this provider, otherwise creates new user.
     * Issues JWT token.
     */
    private String loginOrRegister(OAuthUserInfo userInfo) {

        // Check if userAuthProvider already exists
        Optional<UserAuthProvider> optionalAuthProvider =
                userAuthProviderRepository.findByProviderAndProviderUserId(
                        userInfo.getProvider(), userInfo.getProviderUserId()
                );

        User user;

        if (optionalAuthProvider.isPresent()) {
            user = optionalAuthProvider.get().getUser();
        } else {
            // Create new User
            user = new User();
            user.setEmail(userInfo.getEmail());
            user.setRole(Role.STUDENT); // or assign default role

            // Create UserAuthProvider
            UserAuthProvider authProvider = new UserAuthProvider();
            authProvider.setProvider(userInfo.getProvider());
            authProvider.setProviderUserId(userInfo.getProviderUserId());
            authProvider.setUser(user);

            user.getAuthProviders().add(authProvider);

            // Save user + auth provider
            userRepository.save(user);
        }

        // Generate JWT token
        List<String> roles = List.of("ROLE_" + user.getRole().name());
        return jwtUtil.generateAccessToken(user.getEmail(), roles);
    }
}

