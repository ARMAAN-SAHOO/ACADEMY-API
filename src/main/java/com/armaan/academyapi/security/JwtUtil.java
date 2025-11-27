package com.armaan.academyapi.security;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

   
    @Value("${jwt.access-secret}")
    private String accessSecret;

    @Value("${jwt.refresh-secret}")
    private String refreshSecret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.audience}")
    private String audience;

    @Value("${jwt.access-expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    private Key getAccessKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
    }

    private Key getRefreshKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret));
    }

    
    // ---------------- TOKEN GENERATION ----------------

    public String generateAccessToken(String username, List<String> roles) {
        
                return Jwts.builder()
                .setSubject(username)
                .setIssuer(issuer)
                .setAudience(audience)
                .claim("type", "access")
                .claim("roles", roles)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(getAccessKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer(issuer)
                .setAudience(audience)
                .claim("type", "refresh")
                .setId(UUID.randomUUID().toString()) // Allows blacklist later
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(getRefreshKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractTokenType(String token) {
        return extractClaim(token, claims -> claims.get("type", String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
    String type = peekTokenType(token);

    Key key = "refresh".equals(type) ? getRefreshKey() : getAccessKey();

    return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
}


    // ---------------- VALIDATION ----------------

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public boolean validateAccessToken(String token, String username) {
        return validateToken(token, username, "access", getAccessKey());
    }

    public boolean validateRefreshToken(String token, String username) {
        return validateToken(token, username, "refresh", getRefreshKey());
    }

     private boolean validateToken(String token, String username, String type, Key key) {
        try {
                    Jwts.parserBuilder()
                    .setSigningKey(key)
                    .requireIssuer(issuer)
                    .requireAudience(audience)
                    .requireSubject(username)
                    .require("type", type)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return  !isTokenExpired(token);

        } catch (Exception e) {
            return false;
        }
    }

    public String peekTokenType(String token) {
    try {
        String[] parts = token.split("\\.");
        String payloadJson = new String(Decoders.BASE64URL.decode(parts[1]));

        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(payloadJson);

        return tree.get("type").asText(); // "access" or "refresh"

    } catch (Exception e) {
        return null; // malformed token
    }
}

    public long getRefreshExpiration() {
        return refreshExpiration;
    }

    public String generateTeacherInviteToken(String email) {
    long inviteExpiration = 24 * 60 * 60 * 1000; // 24 hours, adjust as needed

    return Jwts.builder()
            .setSubject(email)                 // the teacher’s email
            .claim("type", "invite")           // token type
            .claim("role", "TEACHER")          // role embedded
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + inviteExpiration))
            .setId(UUID.randomUUID().toString()) // optional unique ID
            .signWith(getAccessKey(), SignatureAlgorithm.HS256)
            .compact();
}
public String validateTeacherInviteToken(String token) {
    try {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getAccessKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String type = claims.get("type", String.class);
        String role = claims.get("role", String.class);
        Date expiry = claims.getExpiration();
        String email = claims.getSubject();

        if (!"invite".equals(type)) {
            throw new RuntimeException("Invalid token type");
        }

        if (!"TEACHER".equals(role)) {
            throw new RuntimeException("Invalid role");
        }

        if (expiry.before(new Date())) {
            throw new RuntimeException("Token expired");
        }

        return email; // return email if token is valid
    } catch (Exception e) {
        throw new RuntimeException("Invalid or tampered invite token");
    }
}

}
