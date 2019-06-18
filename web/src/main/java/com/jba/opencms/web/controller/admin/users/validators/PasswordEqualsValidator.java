package com.jba.opencms.web.controller.admin.users.validators;

import com.jba.opencms.web.controller.admin.users.form.ChangePasswordForm;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Lazy
@Component
public class PasswordEqualsValidator implements ConstraintValidator<PasswordEquals, ChangePasswordForm> {
    @Override
    public void initialize(PasswordEquals constraintAnnotation) {

    }

    @Override
    public boolean isValid(ChangePasswordForm changePasswordForm, ConstraintValidatorContext constraintValidatorContext) {
        return changePasswordForm.getNewPassword().equals(changePasswordForm.getNewPasswordRepeated());
    }
}
