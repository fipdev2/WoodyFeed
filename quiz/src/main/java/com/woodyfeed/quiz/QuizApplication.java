package com.woodyfeed.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class QuizApplication {
	@RestController
	public class HelloWorldController {
		@GetMapping("/hello")
		public String helloWorld() {
			return "Hello, World!";
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

}
