package com.woodyfeed.quiz.quiz;

import java.util.ArrayList;

import com.woodyfeed.quiz.profileMap.ProfileMap;
import com.woodyfeed.quiz.question.Question;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Entity
@Table(name = "quizes")
public class Quiz {
    @Id
    private long id;
    @OneToMany
    private ArrayList<Question> questions;
    private String title;
    private QuizType type;
    private ArrayList<ProfileMap> profileMappings;
}
