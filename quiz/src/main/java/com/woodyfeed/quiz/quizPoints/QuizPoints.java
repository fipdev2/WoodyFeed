package com.woodyfeed.quiz.quizPoints;

import com.woodyfeed.quiz.quiz.Quiz;
import com.woodyfeed.quiz.user.User;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name="quiz_points")
public class QuizPoints {
    private long id;
    private User user;
    private Quiz quiz;
    private int score;
}
