package com.woodyfeed.quiz.Auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woodyfeed.quiz.Http.Requests.LoginRequest;
import com.woodyfeed.quiz.Http.Responses.UserResponse;
import com.woodyfeed.quiz.util.Client;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@Slf4j
@Client
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(
      HttpServletRequest request, HttpServletResponse response, @Valid @RequestBody LoginRequest body) {
    authService.login(request, response, body);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getSession(HttpServletRequest request) {
    return ResponseEntity.ok(authService.getSession(request));
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
    authService.logout(request, response);
    return ResponseEntity.ok().build();
  }

}
