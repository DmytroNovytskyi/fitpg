package com.fitpg.service.impl;

import com.fitpg.dto.WorkoutDto;
import com.fitpg.exception.UserNotFoundException;
import com.fitpg.exception.WorkoutNotFoundException;
import com.fitpg.mapper.WorkoutMapper;
import com.fitpg.model.UserEntity;
import com.fitpg.model.Workout;
import com.fitpg.repository.UserRepository;
import com.fitpg.repository.WorkoutRepository;
import com.fitpg.security.SecurityUtil;
import com.fitpg.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    private static final String ORDER_DESCENDING = "desc";

    private final WorkoutMapper workoutMapper;

    private final WorkoutRepository workoutRepository;

    private final UserRepository userRepository;

    @Transactional
    @Override
    public WorkoutDto getById(long id) {
        Workout workout = workoutRepository.findById(id).orElseThrow(WorkoutNotFoundException::new);
        return workoutMapper.mapWorkoutDto(workout);
    }

    @Transactional
    @Override
    public Page<WorkoutDto> getSortedPage(int page, int size, String sortBy, String order) {
        Sort sort = order.equals(ORDER_DESCENDING) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return workoutRepository.findAllByUserUsername(pageable, SecurityUtil.getSessionUser())
                .map(workoutMapper::mapWorkoutDto);
    }

    @Transactional
    @Override
    public WorkoutDto create(WorkoutDto workoutDto) {
        Workout workout = workoutMapper.mapWorkout(workoutDto);
        UserEntity user = userRepository.findByUsername(SecurityUtil.getSessionUser())
                .orElseThrow(UserNotFoundException::new);
        workout.setUser(user);
        return workoutMapper.mapWorkoutDto(workoutRepository.save(workout));
    }

    @Transactional
    @Override
    public WorkoutDto update(WorkoutDto workoutDto) {
        Workout updating = workoutMapper.mapWorkout(workoutDto);
        Workout persisted = workoutRepository.findById(updating.getId()).orElseThrow(WorkoutNotFoundException::new);
        workoutMapper.mapPresentFields(persisted, updating);
        return workoutMapper.mapWorkoutDto(workoutRepository.save(persisted));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Workout workout = workoutRepository.findById(id).orElseThrow(WorkoutNotFoundException::new);
        workoutRepository.delete(workout);
    }
}
