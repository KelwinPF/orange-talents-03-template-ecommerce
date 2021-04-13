package com.api.mercadolivre.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ExistsSuccessValidator.class)
@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsSuccess {
    String message() default "Esse id de transação já foi bem sucedido";
   
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
