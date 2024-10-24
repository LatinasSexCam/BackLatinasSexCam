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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
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
                                "LatinasSexCam/admin/register",
                                "LatinasSexCam/women/info",
                                "LatinasSexCam/admin/updateInfo",
                                "LatinasSexCam/newComment",
                                "LatinasSexCam/deleteComment/{id}",
                                "LatinasSexCam/admin/deleteWomen/{username}",
                                "LatinasSexCam/service/filterService",
                                "LatinasSexCam/user/info",
                                "LatinasSexCam/editComment/{id}",
                                "LatinasSexCam/women/update",
                                "LatinasSexCam/admin/{id}").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sessionM -> sessionM.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

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
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(false);  // Si es necesario permitir cookies
        configuration.setMaxAge(3600L);  // Cache preflight response

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
