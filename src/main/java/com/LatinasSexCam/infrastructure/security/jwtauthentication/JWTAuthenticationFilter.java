/*
package com.LatinasSexCam.infrastructure.security.jwtauthentication;

import com.LatinasSexCam.infrastructure.exceptions.CustomAuthenticationException;
import com.LatinasSexCam.infrastructure.security.AuthCredentials;
import com.LatinasSexCam.infrastructure.security.TokenUtils;
import com.LatinasSexCam.infrastructure.security.detailsimpl.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException{
        AuthCredentials authCredentials = new AuthCredentials();

        try{
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        }catch (IOException e){
            throw new CustomAuthenticationException("Error al leer los datos de Autenticacion", e);
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException{

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getUsername(),userDetails.getNombre(), userDetails.getRole());

        response.addHeader("Authorization", "Bearer" + token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);

    }
}
*/
