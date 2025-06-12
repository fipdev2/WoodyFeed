package com.woodyfeed.quiz.answer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.woodyfeed.quiz.profile.Profile;
import com.woodyfeed.quiz.profile.ProfileRepository;
import com.woodyfeed.quiz.profileMap.ProfileMap;
import com.woodyfeed.quiz.profileMap.ProfileMapRepository;
import com.woodyfeed.quiz.question.Question;
import com.woodyfeed.quiz.question.QuestionRepository;
import com.woodyfeed.quiz.quiz.Quiz;
import com.woodyfeed.quiz.quiz.QuizRepository;
import com.woodyfeed.quiz.user.UserService;

public class AnswerService {
    @Autowired
    private QuestionRepository questionRepository;
    private UserService userService;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    ProfileMapRepository profileMapRepository;

    public ResponseEntity answerQuestion(long questionId) {

        Question question = questionRepository.findById(questionId);
        question.markQuestionAnswered(userService.getAuthUser());
        Quiz quiz = quizRepository.findByQuestion(question);
        Answer answer = answerRepository.findByQuestion(questionId);
        Profile profile = profileRepository.findById(answer.getMapsToProfile().getId());
        ProfileMap profileMap = profileMapRepository.findByProfile(profile);
        profileMap.sumScore();
        return ResponseEntity.ok().body("Marcada com sucesso");
    }
}
