package ru.sergalas.perpay.security.providers;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.sergalas.perpay.entities.users.entities.User;
import ru.sergalas.perpay.security.constants.SecurityConstants;

import javax.crypto.SecretKey;

@Component
@RequiredArgsConstructor
public class JWTTokenProvider {

    public static final Logger LOG = LoggerFactory.getLogger(JWTTokenProvider.class);


    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final String base64Key = Encoders.BASE64.encode(key.getEncoded());



    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiry = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);
        String userId = user.getId().toString();

        Map<String,Object> claimsMap = new HashMap<>();
        claimsMap.put("id",userId);
        claimsMap.put("username",user.getUsername());


        return Jwts.builder()
                .setSubject(userId)
                .addClaims(claimsMap)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (
                IllegalArgumentException |
                JwtException ex) {
            LOG.error(ex.getMessage());
            return false;
        }
    }

    public UUID getUserIdFromToken(String token) {
        
        Claims claims =  Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return (UUID) claims.get("id");
    }

}
