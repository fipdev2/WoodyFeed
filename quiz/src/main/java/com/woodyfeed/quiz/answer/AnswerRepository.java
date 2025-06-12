package com.woodyfeed.quiz.answer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    public Optional<Answer> findById(long id);

    public Answer findByQuestion(long id);
}
