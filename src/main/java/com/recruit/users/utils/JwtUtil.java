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
    private static final long EXPIRATION_TIME_IN_MILLS = 36000000;

    /**
     *
     * @param token String
     * @return String
     */
    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     *
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return
     */
    public <T> T extractClaim(final String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     *
     * @param token
     * @return
     */
    private Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     *
     * @param username
     * @return
     */
    public String generateToken(final String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");
        claims.put("permissions", "read");
        return createToken(claims, username);
    }

    /**
     * 
     * @param claims
     * @param subject
     * @return
     */
    private String createToken(Map<String, Object> claims, final String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLS))
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     *
     * @param token
     * @param username
     * @return
     */
    public Boolean validateToken(final String token, final String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     *
     * @param token
     * @return
     */
    private Boolean isTokenExpired(final String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
