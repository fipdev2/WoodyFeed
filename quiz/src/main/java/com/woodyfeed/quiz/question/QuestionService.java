package com.woodyfeed.quiz.question;

import org.springframework.beans.factory.annotation.Autowired;

import com.woodyfeed.quiz.answer.Answer;
import com.woodyfeed.quiz.profile.ProfileRepository;
import com.woodyfeed.quiz.question.Requests.QuestionRequest;
import com.woodyfeed.quiz.quiz.QuizRepository;
import com.woodyfeed.quiz.user.User;

public class QuestionService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private QuizRepository quizRepository;

    public Question createQuestion(QuestionRequest question) {
        return Question.builder()
                .description(question.getDescription())
                .quiz(quizRepository.findById(question.getQuizId()))
                .build();
    }

}
