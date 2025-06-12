package com.woodyfeed.quiz.profileMap;

import com.woodyfeed.quiz.profile.Profile;
import com.woodyfeed.quiz.quiz.Quiz;
import com.woodyfeed.quiz.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "profile_maps")
@Entity
public class ProfileMap {
    @Id
    private long id;
    private User user;
    private Quiz quiz;
    @Default
    private int score = 0;
    private Profile profile;

    public void sumScore() {
        this.score++;
    }
}

