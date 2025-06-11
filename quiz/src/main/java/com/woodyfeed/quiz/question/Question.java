package com.woodyfeed.quiz.question;

import com.woodyfeed.quiz.profile.Profile;
import com.woodyfeed.quiz.quiz.Quiz;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name="questions")
public class Question {
    private long id;
    private String option;
    @ManyToOne
    private Profile mapsTo;
    @ManyToOne
    private Quiz quiz;
}
