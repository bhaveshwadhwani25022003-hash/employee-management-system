package com.bhavesh.employee_management.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
    // Secret key — used to sign tokens (keep this secret in real apps!)
    private final String SECRET = "bhavesh_super_secret_key_2026_springboot";

    private SecretKey getKey(){
        System.out.println(Keys.hmacShaKeyFor(SECRET.getBytes()));
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
      // Generate token for a username
      public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)                                    // who is this token for
                .issuedAt(new Date())                                 // when was it created
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // expires in 10 hours
                .signWith(getKey())                                   // sign with secret key
                .compact();
    }
   // Extract username from token
   public String extractUsername(String token) {
    return Jwts.parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    // Check if token is valid
    public boolean isTokenValid(String token) {
        try {
            extractUsername(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
