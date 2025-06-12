package com.woodyfeed.quiz.answer;

import com.woodyfeed.quiz.profile.Profile;
import com.woodyfeed.quiz.question.Question;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "answers")
public class Answer {
    @Id
    private long id;
    private Question question;
    @ManyToOne
    private Profile mapsToProfile;
    private String description;
}
