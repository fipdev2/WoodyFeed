package com.woodyfeed.quiz.profileMap;
import org.springframework.data.jpa.repository.JpaRepository;

import com.woodyfeed.quiz.profile.Profile;

public interface ProfileMapRepository extends JpaRepository<ProfileMap, Long> {

    ProfileMap findByProfile(Profile profile);
}