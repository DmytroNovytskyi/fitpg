package com.fitpg.service.impl;

import com.fitpg.dto.NutrientsTrackDto;
import com.fitpg.exception.NutrientsTrackNotFoundException;
import com.fitpg.exception.UserNotFoundException;
import com.fitpg.mapper.NutrientsTrackMapper;
import com.fitpg.model.NutrientsTrack;
import com.fitpg.model.UserEntity;
import com.fitpg.repository.NutrientsTrackRepository;
import com.fitpg.repository.UserRepository;
import com.fitpg.security.SecurityUtil;
import com.fitpg.service.NutrientsTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NutrientsTrackServiceImpl implements NutrientsTrackService {

    private static final String ORDER_DESCENDING = "desc";

    private final NutrientsTrackRepository nutrientsTrackRepository;

    private final UserRepository userRepository;

    private final NutrientsTrackMapper nutrientsTrackMapper;

    @Transactional
    @Override
    public NutrientsTrackDto getById(long id) {
        NutrientsTrack nutrientsTrack = nutrientsTrackRepository.findById(id)
                .orElseThrow(NutrientsTrackNotFoundException::new);
        return nutrientsTrackMapper.mapNutrientsTrackDto(nutrientsTrack);
    }

    @Transactional
    @Override
    public Page<NutrientsTrackDto> getSortedPage(int page, int size, String sortBy, String order) {
        Sort sort = order.equals(ORDER_DESCENDING) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return nutrientsTrackRepository.findAllByUserUsername(pageable, SecurityUtil.getSessionUser())
                .map(nutrientsTrackMapper::mapNutrientsTrackDto);
    }

    @Transactional
    @Override
    public NutrientsTrackDto create(NutrientsTrackDto nutrientsTrackDto) {
        NutrientsTrack nutrientsTrack = nutrientsTrackMapper.mapNutrientsTrack(nutrientsTrackDto);
        UserEntity user = userRepository.findByUsername(SecurityUtil.getSessionUser())
                .orElseThrow(UserNotFoundException::new);
        nutrientsTrack.setUser(user);
        return nutrientsTrackMapper.mapNutrientsTrackDto(nutrientsTrackRepository.save(nutrientsTrack));
    }

    @Transactional
    @Override
    public NutrientsTrackDto update(NutrientsTrackDto nutrientsTrackDto) {
        NutrientsTrack updating = nutrientsTrackMapper.mapNutrientsTrack(nutrientsTrackDto);
        NutrientsTrack persisted = nutrientsTrackRepository.findById(updating.getId())
                .orElseThrow(NutrientsTrackNotFoundException::new);
        nutrientsTrackMapper.mapPresentFields(persisted, updating);
        return nutrientsTrackMapper.mapNutrientsTrackDto(nutrientsTrackRepository.save(persisted));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        NutrientsTrack nutrientsTrack = nutrientsTrackRepository.findById(id)
                .orElseThrow(NutrientsTrackNotFoundException::new);
        nutrientsTrackRepository.delete(nutrientsTrack);
    }
}
