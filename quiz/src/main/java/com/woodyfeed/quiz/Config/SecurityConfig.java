package com.woodyfeed.quiz.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import com.woodyfeed.quiz.OAuth.Oauth2LoginSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;
    private final UserDetailsService userDetailsService;

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(customizer -> {
            customizer
                    .requestMatchers(antMatcher(HttpMethod.POST, "/users")).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.GET, "/users/verify-email")).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.POST, "/users/forgot-password")).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.PATCH, "/users/reset-password")).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.POST, "/auth/login")).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.GET, "/auth/csrf")).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.GET, "/auth/impersonate")).hasRole("ADMIN")
                    .requestMatchers(antMatcher(HttpMethod.GET, "/auth/impersonate/exit"))
                    .hasRole("PREVIOUS_ADMINISTRATOR")
                    .requestMatchers(antMatcher(HttpMethod.GET, "/notifications/subscribe")).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.POST, "/notifications/delivery/**")).permitAll()
                    .requestMatchers(antMatcher("/swagger-ui/**")).permitAll()
                    .requestMatchers(antMatcher("/v3/api-docs/**")).permitAll()
                    .requestMatchers(antMatcher("/swagger-resources/**")).permitAll()
                    .requestMatchers(antMatcher("/webjars/**")).permitAll()
                    .anyRequest().authenticated();
        });

        http.oauth2Login(customizer -> {
            customizer.successHandler(oauth2LoginSuccessHandler);
        });

        http.exceptionHandling(customizer -> {
            customizer.authenticationEntryPoint(
                    (request, response, authException) -> {
                        response.sendError(401, "Unauthorized");
                    });
        });

        http.cors(customizer -> {
            customizer.configurationSource(corsConfigurationSource());
        });

        return http.build();
    }
}
