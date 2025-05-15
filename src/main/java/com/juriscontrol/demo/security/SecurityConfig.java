package com.juriscontrol.demo.security;

import java.util.Arrays;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpMethod;

import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
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

    // @Bean
    // public CorsConfigurationSource corsConfigurationSource() {
    // CorsConfiguration configuration = new CorsConfiguration();
    // configuration.setAllowedOrigins(List.of("https://juriscontrol.vercel.app",
    // "http://localhost:3000"));
    // configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
    // "OPTIONS"));
    // configuration.setAllowedHeaders(Arrays.asList("Authorization",
    // "Content-Type"));
    // configuration.setAllowCredentials(true);
    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // source.registerCorsConfiguration("/api/**", configuration);
    // return source;
    // }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http
    // .csrf(csrf -> csrf.disable()) // Desabilita CSRF (não recomendado para
    // produção)
    // .headers(headers ->
    // headers.frameOptions(Customizer.withDefaults()).disable()) // Permite o uso
    // de
    // // frames (necessário
    // // para H2 Console)
    // .authorizeHttpRequests(authz -> authz
    // .requestMatchers(
    // "/h2-console/**",
    // "/swagger.html",
    // "/swagger-ui/**",
    // "/v3/api-docs/**",
    // "/api/cadastrar-advogado",
    // "/api/cadastrar-administrador",
    // "/api/login/advogado",
    // "/api/login/admin",
    // "/api/sem-hibernacao")
    // .permitAll() // Permite acesso às rotas de cadastro
    // .requestMatchers("/api/**").authenticated()
    // .anyRequest().authenticated() // Exige autenticação para outras rotas
    // )
    // .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
    // // .httpBasic(Customizer.withDefaults()); // Suporte a autenticação HTTP
    // básica

    // return http.build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(
                            List.of("http://localhost:*", "https://juriscontrol.vercel.app",
                                    "https://backendjuriscontrol.onrender.com"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable()) // Sintaxe corrigida para desabilitar CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/login/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/cadastrar-advogado").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/cadastrar-administrador").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/sem-hibernacao").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
                // .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                // // Descomente se você tem um filtro de segurança personalizado
                .build();
    }
}