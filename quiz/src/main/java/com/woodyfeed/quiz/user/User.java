package com.woodyfeed.quiz.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "users")
public class User {
    private long id;
    private String name;
    private String email;
    private String password;

    User(User userRequest) {
        this.email = userRequest.getEmail();
        this.name = userRequest.getName();
        this.password = new BCryptPasswordEncoder().encode(userRequest.getPassword());
    }
}
