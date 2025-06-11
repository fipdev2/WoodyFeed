package com.woodyfeed.quiz.user.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String email;
    private String name;
    private String password;
}
