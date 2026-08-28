package com.teammesh.TeamMesh.auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expiration;

    public JwtService(@Value("${teammesh.jwt.secret}") String secret,
            @Value("${teammesh.jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    public String generateToken(Long userId, String email) {
        Date now = new Date();
        return Jwts.builder()
                .subject(userId.toString()).claim("email", email).issuedAt(now)
                .expiration(new Date(now.getTime() + expiration)).signWith(secretKey).compact();
    }

    public Long extractUserId(String token){
        Claims claims = extractAllClaims(token);
        return Long.parseLong(claims.getSubject());
    }

    // public boolean isTokenValid(String token){
    //     try{
    //         extractAllClaims(token);
    //         return true;
    //     }catch(Exception exception){
    //         return false;
    //     }
    // }

    public Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }
}