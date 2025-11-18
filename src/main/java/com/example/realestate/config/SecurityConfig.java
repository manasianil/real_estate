package com.example.realestate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers
                                ("/api/users/login",  // ✅ Allow login
                                           "/api/users/register", // ✅ Allow register
                                           "/api/users", // ✅ Allow access to user list
                                           "/api/users/update/{id}", // ✅ Allow update user
                                           "/api/users/delete/{id}", // ✅ Allow delete user

                                           "/api/properties",   // ✅ Allow access to properties list
                                           "/api/properties", // ✅ Allow create property
                                           "/api/properties/{id}", // ✅ Allow get property by ID
                                           "/api/properties/{id}", // ✅ Allow update property
                                           "/api/properties/{id}", // ✅ Allow delete property
                                           "/api/properties/filter").permitAll() // ✅ Allow register
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()); // ✅ Disable CSRF for Postman testing

        return http.build();
    }
}



