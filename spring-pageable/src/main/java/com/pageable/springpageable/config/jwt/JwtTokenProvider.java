package com.pageable.springpageable.config.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtInExpirationInMs;

    public String generateToken(Authentication auth){
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtInExpirationInMs);

        return Jwts.builder()
                .setSubject(String.valueOf(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public int getUserIdFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            log.error("invalid signature token ",e.getMessage());
        }catch (MalformedJwtException e){
            log.error("malformed token ",e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("expired token ",e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("unsupported token ",e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("illegal token ",e.getMessage());
        }
        return false;
    }
}
