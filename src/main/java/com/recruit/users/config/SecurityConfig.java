package com.recruit.users.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @SuppressWarnings({ "deprecation", "removal" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Optional: Disable CSRF if needed for your application
            .authorizeRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/api/signin").permitAll() // Allow access to the login endpoint
                .anyRequest().authenticated() // Other requests require authentication
            )
            .httpBasic(); // Or form login, or whatever authentication method you prefer

        return http.build();
    }
}
