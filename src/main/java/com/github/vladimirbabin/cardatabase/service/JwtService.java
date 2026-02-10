package com.github.vladimirbabin.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 1 day in ms
    private static final String PREFIX = "Bearer ";

    // Generate secret key. Only for the demonstration
    // You should read it from the application configuration
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate signed JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // Get a token from request Authorization header, verify a token and get username
    public String getAuthUser(String token) {
        if (token != null) {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();
        }
        return null;
    }

}
