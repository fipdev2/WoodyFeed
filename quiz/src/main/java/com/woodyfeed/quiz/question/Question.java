package com.woodyfeed.quiz.question;

import java.util.ArrayList;

import com.woodyfeed.quiz.answer.Answer;
import com.woodyfeed.quiz.quiz.Quiz;
import com.woodyfeed.quiz.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@Table(name = "questions")
public class Question {
    @Id
    private long id;
    private String description;
    @ManyToOne
    private Quiz quiz;
    @ManyToMany
    private ArrayList<User> answeredByUsers;
    @OneToMany
    private ArrayList<Answer> answers;

    public boolean markQuestionAnswered(User user) {
        return answeredByUsers.add(user);
    }
}
