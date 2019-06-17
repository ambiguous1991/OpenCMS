package com.jba.opencms.web.controller.admin.systemvariables.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SystemVariableValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueSystemVariable {
    String message() default "Klucz już istnieje!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}