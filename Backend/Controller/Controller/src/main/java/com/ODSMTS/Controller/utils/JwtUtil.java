package com.ODSMTS.Controller.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "/KLpixH9Vo4+x98zfDdxSEPwmxhQVlj6k7C32tl27eQ="; // Secure key

    //private final Key SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(SECRET));


    public String generateToken(String username, int roleId) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roleId", roleId) // ✅ Add roleId
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 3)) // 3 days expiry
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
    
    // Extract roleId from token
    public int extractRoleId(String token) {
        return extractClaims(token).get("roleId", Integer.class);
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    /*public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }*/

    public boolean validateToken(String token, String username, int roleId) {
        try {
            Claims claims = extractClaims(token);
    
            String tokenUsername = claims.getSubject();
            int tokenRoleId = claims.get("roleId", Integer.class);
    
            return username.equals(tokenUsername) && roleId == tokenRoleId && !isTokenExpired(token);
        } catch (Exception e) {
            System.out.println("JWT validation failed: " + e.getMessage());
            return false;
        }
    }
    
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
