package com.woodyfeed.quiz.User.data;

import lombok.Data;

@Data
@PasswordMatch(passwordField = "password", passwordConfirmationField = "confirmPassword")
@Client
public class UpdateUserPasswordRequest {
  private String oldPassword;
  @NotNull
  @Length(min = 8)
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "must contain at least one uppercase letter, one lowercase letter, and one digit.")
  private String password;
  private String confirmPassword;
  private String passwordResetToken;
}