package com.fitpg.service;

import com.fitpg.dto.UserDto;
import org.springframework.data.domain.Page;

/**
 * Service used in manipulations with User entity.
 */
public interface UserService {

    /**
     * Retrieves a user with the specified ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID
     */
    UserDto getById(long id);

    /**
     * Retrieves a sorted and paginated list of users
     *
     * @param page   the page number of the results to retrieve
     * @param size   the maximum number of results per page
     * @param sortBy the field to sort the results by
     * @param order  the sort order
     * @return a page of users sorted and filtered according to the specified parameters
     */
    Page<UserDto> getSortedPage(int page, int size, String sortBy, String order);

    /**
     * Creates a new user
     *
     * @param userDto the user to create
     * @return the newly created user
     */
    UserDto create(UserDto userDto);

    /**
     * Updates an existing user.
     *
     * @param userDto the user to update
     * @return the updated user.
     */
    UserDto update(UserDto userDto);

    /**
     * Deletes a user with the specified ID.
     *
     * @param id the ID of the user to delete
     */
    void deleteById(long id);
}
