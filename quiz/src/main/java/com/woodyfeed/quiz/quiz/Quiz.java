package com.woodyfeed.quiz.quiz;

import java.util.ArrayList;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;

import com.woodyfeed.quiz.profilePoint.ProfilePoint;
import com.woodyfeed.quiz.question.Question;
import com.woodyfeed.quiz.user.User;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "quizes")
public class Quiz {
    @Id
    private long id;
    @OneToMany
    private ArrayList<Question> questions;
    private String title;
    private QuizType type;
}
