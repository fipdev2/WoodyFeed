package com.woodyfeed.quiz.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.woodyfeed.quiz.user.Requests.UserRequest;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private User authUser;

    public ResponseEntity<?> createUser(UserRequest userRequest) {
        if (userRequest.getEmail() == null || userRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body("email and password are required");
        }
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("email already exists");
        }
        try {
            User user = new User();
            user.setEmail(userRequest.getEmail());
            user.setPassword(passwordEncoder().encode(userRequest.getPassword()));
            userRepository.save(user);
            return ResponseEntity.status(201).body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error creating user");
        }
    }

    public ResponseEntity<?> login(@RequestBody String email, @RequestBody String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return ResponseEntity.ok().body("user not found");
        }
        UserDTO userDTO = new UserDTO(user.get());
        if (user.get().getPassword().equals(passwordEncoder().encode(password))) {
            authUser = user.get();
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.ok("wrong password");
    };

    public User getAuthUser() {
        return authUser;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
