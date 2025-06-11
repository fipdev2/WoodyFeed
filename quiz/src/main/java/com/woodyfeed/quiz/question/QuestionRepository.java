package com.woodyfeed.quiz.question;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woodyfeed.quiz.quiz.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz);

    Optional<Question> findById(long id);
}
