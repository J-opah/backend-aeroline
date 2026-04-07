package com.aerolinea.transporte.notificacao_grupos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200", "http://localhost:1664")
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/api/login",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/api-docs",
                                "/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/error"
                        ).permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // <-- ahora funciona correctamente

        return http.build();
    }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return username -> org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password(passwordEncoder().encode("password123"))
                .roles("USER")
                .build();
    }
}