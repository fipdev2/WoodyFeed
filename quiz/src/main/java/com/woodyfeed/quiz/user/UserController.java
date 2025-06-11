package com.woodyfeed.quiz.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.woodyfeed.quiz.user.Requests.UserRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from UserController");
    }

    @PostMapping("/")
    public ResponseEntity<?> postMethodName(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }

}
