package com.fitpg.mapper;

import com.fitpg.dto.UserDto;
import com.fitpg.model.Role;
import com.fitpg.model.User;
import com.fitpg.repository.RoleRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A mapper interface for converting between User and UserDto objects.
 * Also allows to map present fields for User objects.
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class UserMapper {

    /**
     * An instance of the mapper.
     */
    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Autowired
    protected RoleRepository roleRepository;

    /**
     * Converts a User object to a UserDto object ignoring password.
     *
     * @param user The User object to convert
     * @return The corresponding UserDto object
     */
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", qualifiedByName = "rolesToStrings")
    public abstract UserDto mapUserDto(User user);

    /**
     * Converts a UserDto object to a User object.
     *
     * @param userDto The UserDto object to convert
     * @return The corresponding User object
     */
    @Mapping(target = "roles", qualifiedByName = "stringsToRoles")
    public abstract User mapUser(UserDto userDto);

    /**
     * Copies the present fields of a User object from another User object.
     *
     * @param toUser   The User object to copy the fields to
     * @param fromUser The User object to copy the fields from
     */
    public abstract void mapPresentFields(@MappingTarget User toUser, User fromUser);

    @Named("rolesToStrings")
    protected Set<String> mapRolesToStrings(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    @Named("stringsToRoles")
    protected Set<Role> mapStringsToRoles(Set<String> roles) {
        return roleRepository.findAllByNameIn(roles);
    }
}
