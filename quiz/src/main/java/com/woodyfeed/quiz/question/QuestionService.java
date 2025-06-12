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

    @Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(QuestionRequest questionRequest) {
        try {
            Question question = Question.builder()
                    .description(questionRequest.getDescription())
                    .quiz(quizRepository.findById(questionRequest.getQuizId()))
                    .build();
            questionRepository.save(question);
            return question;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
