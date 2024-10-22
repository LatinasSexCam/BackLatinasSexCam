package com.LatinasSexCam.infrastructure.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/",
                                "LatinasSexCam/user/register",
                                "LatinasSexCam/user/login",
                                "LatinasSexCam/women/register",
                                "LatinasSexCam/comments",
                                "LatinasSexCam/packages",
                                "LatinasSexCam/women/womens",
                                "LatinasSexCam/women/count",
                                "LatinasSexCam/service/services",
                                "LatinasSexCam/user/subServices/{serviceId}",
                                "LatinasSexCam/women/filter",
                                "LatinasSexCam/user/filters",
                                "LatinasSexCam/service/filterService").permitAll()
                        .requestMatchers("LatinasSexCam/newComment",
                                "LatinasSexCam/editComment/{id}",
                                "LatinasSexCam/deleteComment/{id}",
                                "LatinasSexCam/women/update",
                                "LatinasSexCam/user/info",
                                "LatinasSexCam/women/info").authenticated()
                        .requestMatchers(HttpMethod.POST, "LatinasSexCam/admin/register",
                                "LatinasSexCam/admin/updateInfo").hasAuthority("admin")
                        .requestMatchers(HttpMethod.DELETE, "LatinasSexCam/admin/{id}",
                                "LatinasSexCam/admin/deleteWomen").hasAuthority("admin")
                        .anyRequest().authenticated())
                .sessionManagement(sessionM -> sessionM.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

/*    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }*/

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }


    @Bean
    PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
}
