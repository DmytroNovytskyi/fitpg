package com.fitpg.repository;

import com.fitpg.model.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, Long> {

    Set<MuscleGroup> findAllByNameIn(Set<String> names);
}
