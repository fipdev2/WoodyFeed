package com.woodyfeed.quiz.Http.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @Email
    @NotNull
    private String email;
    private String password;
}
