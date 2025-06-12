package com.woodyfeed.quiz.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woodyfeed.quiz.quiz.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz);

    Question findById(long id);
}
