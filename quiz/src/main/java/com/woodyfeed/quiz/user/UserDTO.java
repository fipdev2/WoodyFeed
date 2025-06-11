package com.woodyfeed.quiz.user;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String email;

    UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
