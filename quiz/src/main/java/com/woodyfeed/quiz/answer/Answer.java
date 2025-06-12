package com.woodyfeed.quiz.answer;

import com.woodyfeed.quiz.profile.Profile;
import com.woodyfeed.quiz.question.Question;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "answers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {
    @Id
    private long id;
    private Question question;
    @ManyToOne
    private Profile mapsToProfile;
    private String description;
}
