package com.woodyfeed.quiz.OAuth;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.woodyfeed.quiz.User.User;
import com.woodyfeed.quiz.Config.ApplicationProperties;

import com.woodyfeed.quiz.User.UserRepository;

@Component
@Slf4j
public class Oauth2LoginSuccessHandler implements AuthenticationSuccessHandler { 
    private final ApplicationProperties applicationProperties;
    private final UserRepository userRepository;

    public Oauth2LoginSuccessHandler(ApplicationProperties applicationProperties,UserRepository userRepository) {
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
        String email = authenticationToken.getPrincipal().getAttribute("email");
        User existingUser = userRepository.findByEmail(email).orElse(null);

        if (existingUser != null) {
            authenticateUser(existingUser, response);
        } else {
            User newUser = createUserFromOauth2User(authenticationToken);
            authenticateUser(newUser, response);
        }
    }

    private void authenticateUser(User user, HttpServletResponse response) throws IOException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        response.sendRedirect(applicationProperties.getLoginSuccessUrl());
    }

    private User createUserFromOauth2User(OAuth2AuthenticationToken authentication) {
        User user = new User(authentication.getPrincipal());
        return user;
    }

}
