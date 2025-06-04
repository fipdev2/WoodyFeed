package com.woodyfeed.quiz.User.data;

import lombok.Data;

@Data
@Client
public class ForgotPasswordRequest {
  @Email
  private String email;
}
