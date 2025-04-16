package com.example.deliverify.security.config;

import com.example.deliverify.security.jwt.JwtAuthenticationEntryPoint;
import com.example.deliverify.security.jwt.JwtAuthenticationFilter;
import com.example.deliverify.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/*
.csrf(csrf -> csrf.disable())

CSRF protection is disabled since we're using token-based stateless auth (not sessions).

.exceptionHandling(...)

Registers the JwtAuthenticationEntryPoint to handle unauthorized access.

.sessionManagement(...)

Ensures app is stateless (no sessions stored on the server).

.authorizeHttpRequests(...)

Allows unauthenticated access to /api/auth/** routes (like login/register)

Requires all other requests to be authenticated.

http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

Registers our JwtAuthenticationFilter before Spring's built-in username-password filter, so we can inject JWT-based logic.
 */
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth ->
                    auth
                            .requestMatchers("/api/auth/**").permitAll() // ✅ allow /auth/*
                            .anyRequest().authenticated()
            );

    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

}
