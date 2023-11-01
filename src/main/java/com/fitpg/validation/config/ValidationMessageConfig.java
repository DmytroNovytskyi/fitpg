package com.fitpg.validation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configuration class for setting up validation messages.
 */
@Configuration
@RequiredArgsConstructor
public class ValidationMessageConfig {

    private final MessageSource messageSource;

    /**
     * Creates a validator bean that uses the message source defined in {@link com.fitpg.config.LocaleConfig}.
     *
     * @return a LocalValidatorFactoryBean bean
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
