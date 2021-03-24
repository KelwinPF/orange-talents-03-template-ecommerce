package com.api.mercadolivre.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    String message() default "Campo já existente";
    
    String table();
    String column();
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}