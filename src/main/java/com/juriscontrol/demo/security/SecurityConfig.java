package com.juriscontrol.demo.security;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import io.jsonwebtoken.security.Keys;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil; // Injeção da dependência
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtUtil.getJwtSecret().getBytes());
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http
    // .csrf(csrf -> csrf.disable())
    // .authorizeHttpRequests(authz -> authz
    // // .requestMatchers(
    // // "/auth/login",
    // // "/swagger-ui/**",
    // // "/v3/api-docs/**",
    // // "/adm/cadastrar",
    // // "/swagger.html"
    // // "http://localhost:8080/adm/cadastrar",
    // // "/adm/cadastrar"
    // // ).permitAll()
    // // .requestMatchers("/adm/cadastrar").permitAll()
    // // .anyRequest().authenticated()
    // .anyRequest().permitAll()
    // )
    // .httpBasic(Customizer.withDefaults());
    // // .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())); //
    // suporte ao JWT
    // System.out.println("Security Filter Chain Configured Successfully");
    // return http.build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF (não recomendado para produção)
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable()) // Permite o uso de
                                                                                               // frames (necessário
                                                                                               // para H2 Console)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/h2-console/**",
                                "/swagger.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                // "/administradores/cadastrar",
                                "/auth/login",
                                "/auth/login/advogado",
                                "/auth/login/admin",
                                "/administradores/cadastrar",
                                "/advogados/cadastrar"
                        // "/advogados/cadastrar",
                        // "/clientes/cadastrar"
                        ).permitAll() // Permite acesso às rotas de cadastro
                        .anyRequest().authenticated() // Exige autenticação para outras rotas
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        // .httpBasic(Customizer.withDefaults()); // Suporte a autenticação HTTP básica

        return http.build();
    }
}