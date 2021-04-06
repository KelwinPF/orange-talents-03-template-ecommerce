package com.api.mercadolivre.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CaracteristicsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Repeated {
    String message() default "Caracteristicas repetidas";
       
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
