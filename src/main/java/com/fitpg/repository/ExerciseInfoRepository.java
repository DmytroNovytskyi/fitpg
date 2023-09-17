package com.fitpg.repository;

import com.fitpg.model.ExerciseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ExerciseInfoRepository extends JpaRepository<ExerciseInfo, Long> {

    Set<ExerciseInfo> findAllByNameIn(Set<String> names);
}
