package com.woodyfeed.quiz.profile;

import com.woodyfeed.quiz.question.Question;

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
@Table(name="profiles")
public class Profile {
    private long id;
    private String universe;
    private String name;
    private String imageFileName;
    @OneToMany
    private Question question;
}
