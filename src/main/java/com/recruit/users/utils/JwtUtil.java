package com.recruit.users.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final byte[] secretKey = "mysecretkeymysecretkeymysecretkey".getBytes(StandardCharsets.UTF_8);

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");
        claims.put("permissions", "read");
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        try {
            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10-hour expiration
                    .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            System.err.println(e);
        }
        return "some token value";
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
