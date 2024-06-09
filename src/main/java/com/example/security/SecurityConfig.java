package com.example.security;

import com.example.models.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
            "/api/login/**",
            "/api/register/**",
            "/**"

    };
    private static final String[] POST_USER_ADMIN = {
            "/api/**"

    };
    private static final String[] PUT_USER_ADMIN = {
            "/api/**"

    };
    private static final String[] DELETE_ADMIN = {
            "/api/**"

    };
    private static final String[] GET_USER_ADMIN = {
            "/api/**"

    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.headers(x -> x.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x -> x
                        .requestMatchers(AUTH_WHITE_LIST).permitAll()
                       // .requestMatchers(HttpMethod.GET,GET_USER_ADMIN).hasAnyRole(Role.ROLE_ADMIN.getValue(), Role.ROLE_USER.getValue())
                        //.requestMatchers(HttpMethod.POST,POST_USER_ADMIN).hasAnyRole(Role.ROLE_ADMIN.getValue(), Role.ROLE_USER.getValue())
                       // .requestMatchers(HttpMethod.PUT, PUT_USER_ADMIN).hasAnyRole(Role.ROLE_ADMIN.getValue(), Role.ROLE_USER.getValue())
                        //.requestMatchers(HttpMethod.DELETE,DELETE_ADMIN).hasRole(Role.ROLE_ADMIN.getValue())
                        .anyRequest().authenticated()

        ).formLogin(AbstractHttpConfigurer::disable).httpBasic(Customizer.withDefaults());
        return security.build();


    }
}
