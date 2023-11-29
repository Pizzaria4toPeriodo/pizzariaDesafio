package com.mensal.pizzaria.config.jwt;

import com.mensal.pizzaria.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceGenerator {
    public String generateToken(UserEntity entity) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", entity.getId().toString());
        extraClaims.put("username", entity.getUsername());
        extraClaims.put("role", entity.getRole());

        return Jwts.builder().setClaims(extraClaims).setSubject(entity.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(new Date().getTime() + 3600000 * JwtConfig.HORAS_EXPIRACAO_TOKEN)).signWith(getSigningKey(), JwtConfig.ALGORITMO_ASSINATURA).compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtConfig.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
}