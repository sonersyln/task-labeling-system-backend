package com.example.security;

import com.example.models.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "swagger-ui.html",
            "/swagger-ui/**",
            "/",
            "index.html",
            "/images/**",
            "/css/**",
            "/js/**",
            "/contactMessages/save",
            "/auth/login",
            "/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x
                        .requestMatchers(AUTH_WHITE_LIST).permitAll()
                        .requestMatchers("/private/**").hasAnyRole(Role.ROLE_ADMIN.getValue(), Role.ROLE_USER.getValue())
                        .anyRequest().authenticated()

        ).formLogin(AbstractHttpConfigurer::disable).httpBasic(Customizer.withDefaults());
        return security.build();


    }
}
