package com.fitpg.mapper;

import com.fitpg.dto.UserDto;
import com.fitpg.exception.RolesNotFoundException;
import com.fitpg.model.Role;
import com.fitpg.model.UserEntity;
import com.fitpg.repository.RoleRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A mapper interface for converting between User and UserDto objects.
 * Also allows to map present fields for User objects.
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    /**
     * Converts a User object to a UserDto object ignoring password.
     *
     * @param user The User object to convert
     * @return The corresponding UserDto object
     */
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", qualifiedByName = "rolesToStrings")
    public abstract UserDto mapUserDto(UserEntity user);

    /**
     * Converts a UserDto object to a User object.
     *
     * @param userDto The UserDto object to convert
     * @return The corresponding User object
     */
    @Mapping(target = "roles", qualifiedByName = "stringsToRoles")
    @Mapping(target = "password", qualifiedByName = "encodePassword")
    public abstract UserEntity mapUser(UserDto userDto);

    /**
     * Copies the present fields of a User object from another User object.
     *
     * @param toUser   The User object to copy the fields to
     * @param fromUser The User object to copy the fields from
     */
    public abstract void mapPresentFields(@MappingTarget UserEntity toUser, UserEntity fromUser);

    @Named("rolesToStrings")
    protected Set<String> rolesToStrings(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    @Named("stringsToRoles")
    protected Set<Role> stringsToRoles(Set<String> roles) {
        if (roles != null) {
            Set<Role> roleEntities = roleRepository.findAllByNameIn(roles);
            if (roles.size() != roleEntities.size()) {
                Set<String> acceptedRoles = new HashSet<>(roles);
                Set<String> returnedRoles = roleEntities.stream().map(Role::getName).collect(Collectors.toSet());
                acceptedRoles.removeAll(returnedRoles);
                throw new RolesNotFoundException(acceptedRoles);
            }
            return roleEntities;
        }
        return null;
    }

    @Named("encodePassword")
    protected String encodePassword(String password) {
        if (password != null) {
            return passwordEncoder.encode(password);
        }
        return null;
    }
}
