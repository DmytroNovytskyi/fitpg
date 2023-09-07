package com.fitpg.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IsValueOfEnumValidator implements ConstraintValidator<IsValueOfEnum, String> {

    private Set<String> values;

    @Override
    public void initialize(IsValueOfEnum constraintAnnotation) {
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || values.contains(value);
    }
}
