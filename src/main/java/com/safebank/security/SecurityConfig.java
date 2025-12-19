package com.safebank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitamos CSRF por simplicidad en este MVP y para facilitar pruebas de
                // API con Postman/Swagger.
                // En producción real, se debería habilitar y configurar correctamente.
                .csrf(csrf -> csrf.disable())

                // Configuración de permisos de rutas
                .authorizeHttpRequests(auth -> auth
                        // Recursos estáticos y rutas públicas
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/", "/login", "/registro").permitAll()
                        .requestMatchers("/api/usuarios/registro").permitAll() // Permitir registro por API

                        // Consola H2 y Swagger UI
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated())

                // Configuración del formulario de Login
                .formLogin(form -> form
                        .loginPage("/login") // Ruta de nuestra página de login personalizada
                        .defaultSuccessUrl("/dashboard", true) // Redirigir al dashboard tras login exitoso
                        .permitAll())

                // Configuración de Logout
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())

                // Configuración necesaria para que la consola H2 funcione (usa frames)
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}
