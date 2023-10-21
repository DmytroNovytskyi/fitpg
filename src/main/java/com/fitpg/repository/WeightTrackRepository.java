package com.fitpg.repository;

import com.fitpg.model.WeightTrack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightTrackRepository extends JpaRepository<WeightTrack, Long> {

    /**
     * Finds all weight trackers for specified user.
     *
     * @param pageable Page information
     * @param username The username
     * @return page of workouts for specified user
     */
    Page<WeightTrack> findAllByUserUsername(Pageable pageable, String username);
}
