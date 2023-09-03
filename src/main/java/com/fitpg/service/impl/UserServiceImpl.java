package com.fitpg.service.impl;

import com.fitpg.dto.UserDto;
import com.fitpg.exception.UserAlreadyExistsException;
import com.fitpg.exception.UserNotFoundException;
import com.fitpg.mapper.UserMapper;
import com.fitpg.model.User;
import com.fitpg.repository.UserRepository;
import com.fitpg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String ORDER_DESCENDING = "desc";
    /**
     * The UserRepository object that will be used to interact with users in database.
     */
    private final UserRepository userRepository;

    /**
     * Retrieves a user with the specified ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    @Transactional
    @Override
    public UserDto getById(long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    /**
     * Retrieves a sorted and paginated list of users
     *
     * @param page   the page number of the results to retrieve
     * @param size   the maximum number of results per page
     * @param sortBy the field to sort the results by
     * @param order  the sort order
     * @return a page of users sorted and filtered according to the specified parameters
     */
    @Transactional
    @Override
    public Page<UserDto> getSortedPage(int page, int size, String sortBy, String order) {
        Sort sort = order.equals(ORDER_DESCENDING) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable).map(UserMapper.INSTANCE::mapUserDto);
    }

    /**
     * Creates a new user
     *
     * @param userDto the user to create
     * @return the newly created user
     * @throws UserAlreadyExistsException if a user with the same username or email already exists
     */
    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        User user = UserMapper.INSTANCE.mapUser(userDto);
        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        return UserMapper.INSTANCE.mapUserDto(userRepository.save(user));
    }

    /**
     * Updates an existing user.
     *
     * @param userDto the user to update
     * @return the updated user.
     * @throws UserNotFoundException      if the user with the specified ID is not found.
     * @throws UserAlreadyExistsException if a user with the same email already exists.
     */
    @Transactional
    @Override
    public UserDto update(UserDto userDto) {
        User updating = UserMapper.INSTANCE.mapUser(userDto);
        User persisted = userRepository.findById(updating.getId()).orElseThrow(UserNotFoundException::new);

        String updatingEmail = updating.getEmail() == null ? persisted.getEmail() : updating.getEmail();
        if (userRepository.existsByEmailAndIdIsNot(updatingEmail, persisted.getId())) {
            throw new UserAlreadyExistsException();
        }

        UserMapper.INSTANCE.mapPresentFields(persisted, updating);
        return UserMapper.INSTANCE.mapUserDto(userRepository.save(persisted));
    }

    /**
     * Deletes a user with the specified ID.
     *
     * @param id the ID of the user to delete
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    @Transactional
    @Override
    public void deleteById(long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
