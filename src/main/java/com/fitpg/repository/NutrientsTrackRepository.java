package com.fitpg.repository;

import com.fitpg.model.NutrientsTrack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientsTrackRepository extends JpaRepository<NutrientsTrack, Long> {

    /**
     * Finds all nutrients trackers for specified user.
     *
     * @param pageable Page information
     * @param username The username
     * @return page of nutrients tracks for specified user
     */
    Page<NutrientsTrack> findAllByUserUsername(Pageable pageable, String username);
}
