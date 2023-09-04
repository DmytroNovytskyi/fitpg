package com.fitpg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fitpg.validation.group.OnCreate;
import com.fitpg.validation.group.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Set;

/**
 * Data transfer object for User entity.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    /**
     * Username validation regex.
     */
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,8}[a-zA-Z0-9]$";

    /**
     * Email validation regex.
     */
    private static final String EMAIL_REGEX = "^(?=[a-zA-Z0-9._@%-]{6,255}$)[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$";

    /**
     * Password validation regex.
     */
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,32}$";

    /**
     * User's unique identifier. Must be null on create and not null on update.
     */
    @Null(message = "{users.id.null}", groups = OnCreate.class)
    @NotNull(message = "{users.id.notNull}", groups = OnUpdate.class)
    private Long id;

    /**
     * User's username. Must be not null and valid format on create and null on update.
     */
    @Null(message = "{users.username.null}", groups = OnUpdate.class)
    @NotNull(message = "{users.username.notNull}", groups = OnCreate.class)
    @Pattern(message = "{users.username.pattern}", regexp = USERNAME_REGEX)
    private String username;

    /**
     * User's email address. Must be not null and valid email format on create.
     */
    @NotNull(message = "{users.email.notNull}", groups = OnCreate.class)
    @Pattern(message = "{users.email.pattern}",
            regexp = EMAIL_REGEX)
    private String email;

    /**
     * User password. Must be not null and valid password format on create.
     */
    @NotNull(message = "{users.password.notNull}", groups = OnCreate.class)
    @Pattern(message = "{users.password.pattern}",
            regexp = PASSWORD_REGEX)
    private String password;

    /**
     * User roles. Must be null on create.
     */
    @NotNull(message = "{users.roles.null}", groups = OnCreate.class)
    private Set<String> roles;
}
