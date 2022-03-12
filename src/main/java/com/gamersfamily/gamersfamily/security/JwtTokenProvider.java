package com.gamersfamily.gamersfamily.security;

import com.gamersfamily.gamersfamily.exception.BlogAPIException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMilliseconds;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMilliseconds);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            throw new BlogAPIException("Invalid JWT Signature", HttpStatus.BAD_REQUEST);
        }catch (MalformedJwtException ex){
            throw new BlogAPIException("Invalid JWT Token", HttpStatus.BAD_REQUEST);
        }catch (ExpiredJwtException ex){
            throw new BlogAPIException("Expired JWT Token", HttpStatus.BAD_REQUEST);
        }catch (UnsupportedJwtException ex){
            throw new BlogAPIException("Unsupported JWT Token", HttpStatus.BAD_REQUEST);
        }catch (IllegalArgumentException ex){
            throw new BlogAPIException("JWT claims string is empty", HttpStatus.BAD_REQUEST);
        }

    }
}
