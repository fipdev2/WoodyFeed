package com.woodyfeed.quiz.quiz;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woodyfeed.quiz.user.User;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public Quiz findByUser(User user);
    public Quiz findById(long id);
}
