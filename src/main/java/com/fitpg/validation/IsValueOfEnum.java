package com.fitpg.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsValueOfEnumValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsValueOfEnum {

    String fieldName();

    Class<? extends Enum<?>> enumClass();

    String message() default "{fieldName} {isValueOfEnum.constraint} {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
