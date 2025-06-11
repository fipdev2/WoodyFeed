package com.woodyfeed.quiz.quiz.Requests;

import java.util.ArrayList;

import com.woodyfeed.quiz.question.Question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizRequest {
    private String quizType;
    private String question;
    private ArrayList<Question> questions;
    private String title;
}
