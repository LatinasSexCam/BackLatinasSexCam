package com.LatinasSexCam.infrastructure.security;


import com.LatinasSexCam.domain.model.Role;
import com.LatinasSexCam.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class TokenUtils {

    private final static  String ACCESS_TOKEN_SECRET = "4qhq8LrEBfTcaRHxhdb9zURb2rf8e7Ud";
    private final static Long ACCES_TOKEN_VALIDITY_SECONDS= 2_592_000L;

    public String getTokenUser(User user){
        return createToken( user.getUser_name(), user.getEmail(), user.getRole());
    }

    public static String createToken(String nombre, String email, Role rol){
        long expirationTime = ACCES_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String , Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
        extra.put("rol", rol);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }
}
