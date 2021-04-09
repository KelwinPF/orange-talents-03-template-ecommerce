package com.api.mercadolivre.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmEstoqueValidator.class)
@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmEstoque {
    String message() default "Não exite produto em estoque para essa quantidade";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
	