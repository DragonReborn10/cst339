package com.gcu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures Spring Security for the Soldier Management System.
 *
 * Provides authentication requirements, login and logout
 * configuration, password encoding, and CSRF protection.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures HTTP security rules for the application.
     *
     * Login, Registration, and required static resources
     * are publicly accessible.
     *
     * All other application pages require authentication.
     *
     * @param http Spring Security HTTP configuration
     * @return configured security filter chain
     * @throws Exception if configuration fails
     */
    /**
     * Stateless HTTP Basic security for Milestone 7 read-only REST APIs.
     * Uses the same database-backed UserDetailsService and BCrypt encoder
     * as the browser login. No session or CSRF token is needed for GET APIs.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            /*
             * Configure authorization for application routes.
             */
            .authorizeHttpRequests(authorize -> authorize

                // Public login and registration pages
                .requestMatchers(
                    "/",
                    "/login",
                    "/register",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/favicon.ico"
                ).permitAll()

                // Every other endpoint requires authentication
                .anyRequest().authenticated()
            )

            /*
             * Configure the custom login page.
             *
             * Spring Security will process POST /login
             * once the existing controller is refactored.
             */
            .formLogin(form -> form

                .loginPage("/login")

                .loginProcessingUrl("/login")

                .usernameParameter("username")

                .passwordParameter("password")

                .defaultSuccessUrl("/dashboard", true)

                .failureUrl("/login?error")

                .permitAll()
            )

            /*
             * Configure logout.
             *
             * Spring Security will invalidate the session
             * and remove authentication information.
             */
            .logout(logout -> logout

                .logoutUrl("/logout")

                .logoutSuccessUrl("/login?logout")

                .invalidateHttpSession(true)

                .deleteCookies("JSESSIONID")

                .permitAll()
            );

        /*
         * CSRF protection remains enabled by default.
         * Thymeleaf POST forms must include CSRF tokens.
         */

        return http.build();
    }

    /**
     * Creates the password encoder used for secure
     * password storage and verification.
     *
     * @return BCrypt password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}