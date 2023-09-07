package com.fitpg.repository;

import com.fitpg.model.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    /**
     * Finds all workouts for specified user.
     *
     * @param pageable Page information
     * @param username The username
     * @return page of workouts for specified user
     */
    Page<Workout> findAllByUserUsername(Pageable pageable, String username);
}
