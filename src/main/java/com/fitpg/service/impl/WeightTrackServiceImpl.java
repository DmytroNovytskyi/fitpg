package com.fitpg.service.impl;

import com.fitpg.dto.WeightTrackDto;
import com.fitpg.exception.UserNotFoundException;
import com.fitpg.exception.WeightTrackNotFoundException;
import com.fitpg.mapper.WeightTrackMapper;
import com.fitpg.model.UserEntity;
import com.fitpg.model.WeightTrack;
import com.fitpg.repository.UserRepository;
import com.fitpg.repository.WeightTrackRepository;
import com.fitpg.security.SecurityUtil;
import com.fitpg.service.WeightTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WeightTrackServiceImpl implements WeightTrackService {

    private static final String ORDER_DESCENDING = "desc";

    private final WeightTrackRepository weightTrackRepository;

    private final UserRepository userRepository;

    private final WeightTrackMapper weightTrackMapper;

    @Transactional
    @Override
    public WeightTrackDto getById(long id) {
        WeightTrack weightTrack = weightTrackRepository.findById(id).orElseThrow(WeightTrackNotFoundException::new);
        return weightTrackMapper.mapWeightTrackDto(weightTrack);
    }

    @Transactional
    @Override
    public Page<WeightTrackDto> getSortedPage(int page, int size, String sortBy, String order) {
        Sort sort = order.equals(ORDER_DESCENDING) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return weightTrackRepository.findAllByUserUsername(pageable, SecurityUtil.getSessionUser())
                .map(weightTrackMapper::mapWeightTrackDto);
    }

    @Transactional
    @Override
    public WeightTrackDto create(WeightTrackDto weightTrackDto) {
        WeightTrack weightTrack = weightTrackMapper.mapWeightTrack(weightTrackDto);
        UserEntity user = userRepository.findByUsername(SecurityUtil.getSessionUser())
                .orElseThrow(UserNotFoundException::new);
        weightTrack.setUser(user);
        return weightTrackMapper.mapWeightTrackDto(weightTrackRepository.save(weightTrack));
    }

    @Transactional
    @Override
    public WeightTrackDto update(WeightTrackDto weightTrackDto) {
        WeightTrack updating = weightTrackMapper.mapWeightTrack(weightTrackDto);
        WeightTrack persisted = weightTrackRepository.findById(updating.getId())
                .orElseThrow(WeightTrackNotFoundException::new);
        weightTrackMapper.mapPresentFields(persisted, updating);
        return weightTrackMapper.mapWeightTrackDto(weightTrackRepository.save(persisted));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        WeightTrack weightTrack = weightTrackRepository.findById(id).orElseThrow(WeightTrackNotFoundException::new);
        weightTrackRepository.delete(weightTrack);
    }
}
