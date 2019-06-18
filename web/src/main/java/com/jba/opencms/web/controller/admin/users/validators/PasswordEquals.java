package com.jba.opencms.web.controller.admin.users.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordEqualsValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordEquals {
    String message() default "Hasła nie są identyczne!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
