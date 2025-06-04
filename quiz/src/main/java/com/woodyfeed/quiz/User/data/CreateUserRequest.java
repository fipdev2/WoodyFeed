package com.woodyfeed.quiz.User.data;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@PasswordMatch(passwordField = "password", passwordConfirmationField = "passwordConfirmation")
@Builder
@Client
public class CreateUserRequest {
  @email
  @Unique(columnName = "email", tableName = "user", message = "User with this email already exists")
  private String email;
  @NotNull
  @Length(min = 8)
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "must contain at least one uppercase letter, one lowercase letter, and one digit.")
  private String password;
  private String passwordConfirmation;
  @Nullable
  private String firstName;
  @Nullable
  private String lastName;
}
