package com.dicka.jjwtbackend.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${company.app.jwtSecret}")
    private String jwtSecret;

    @Value("${company.app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateJwtToken(Authentication auth){

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            log.error("invalid signature jwt : {} ", e.getMessage());
        }catch (MalformedJwtException e){
            log.error("malformed jwt : {}", e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("expiration jwt : {}", e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("unsupported jwt : {}", e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("Jwt claims string is empty : {}",e.getMessage());
        }
        return false;
    }
}
