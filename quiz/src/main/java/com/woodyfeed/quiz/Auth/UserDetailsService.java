package com.woodyfeed.quiz.Auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;


import com.woodyfeed.quiz.User.User;
import com.woodyfeed.quiz.User.UserRepository;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws BadCredentialsException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Cannot find user with email " + email));
        return user;
    }

}
