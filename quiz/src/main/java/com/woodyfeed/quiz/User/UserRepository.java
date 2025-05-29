package com.woodyfeed.quiz.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT FROM user WHERE email = :email")
    Optional<User> findByEmail(@Param("email")String email);
}
