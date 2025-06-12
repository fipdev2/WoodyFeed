package com.woodyfeed.quiz.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.woodyfeed.quiz.quiz.Requests.QuizRequest;
import com.woodyfeed.quiz.user.User;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    private User authUser;

    public ResponseEntity<?> createQuiz(@RequestBody QuizRequest quizRequest) {
        Quiz quiz = Quiz.builder()
                .title(quizRequest.getTitle())
                .type(QuizType.BATMAN)
                .questions(quizRequest.getQuestions())
                .build();

        quizRepository.save(quiz);

        return ResponseEntity.ok("Quiz created successfully");
    }
}
