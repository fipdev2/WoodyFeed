package com.woodyfeed.quiz.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findById(long id);

    Optional<Profile> findByUniverse(String universe);
    @Override
    List<Profile> findAll();
}
