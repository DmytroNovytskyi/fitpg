package com.fitpg.repository;

import com.fitpg.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Checks if a user exists in the repository by their username or email.
     *
     * @param username The user's username
     * @param email    The user's email address
     * @return true if the user exists, false otherwise
     */
    boolean existsByUsernameOrEmail(String username, String email);

    /**
     * Checks if a user exists in the repository by their email and excluding a specific ID.
     *
     * @param email The user's email address
     * @param id    The ID to exclude from the check
     * @return true if the user exists, false otherwise
     */
    boolean existsByEmailAndIdIsNot(String email, Long id);

    Optional<UserEntity> findByUsername(String username);
}
