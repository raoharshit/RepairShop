package com.repairshoptest.utils;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "yourSuperSecretKeyThatIsAtLeast256BitsLong"; // Store securely
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour expiration

    private SecretKey getSigningKey() {
        // Convert the secret key to bytes and create a SecretKey instance
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    
    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId) // Set the user ID as the subject
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(),SignatureAlgorithm.HS256) // Sign with HS256 and the secret key
                .compact();
    }

    public Claims validateToken(String jwt) {
        try {
            // Parse the token, validating signature and expiration
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            claims.getExpiration().before(new Date());
            // Check if the token is expired
            return claims;

        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature.");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token.");
        } catch (ExpiredJwtException ex) {
            System.out.println("JWT token is expired.");
        } catch (UnsupportedJwtException ex) {
            System.out.println("JWT token is unsupported.");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        
        return null; // Return false if any validation checks fail
    }
    
    
}
