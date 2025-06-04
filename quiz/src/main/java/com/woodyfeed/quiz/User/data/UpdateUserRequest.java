package com.woodyfeed.quiz.User.data;

import lombok.Data;

@Data
@Client
public class UpdateUserRequest {
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
}