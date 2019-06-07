package com.jba.opencms.web.controller.admin.globals.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GlobalsValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueGlobal {
    String message() default "Klucz już istnieje!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}