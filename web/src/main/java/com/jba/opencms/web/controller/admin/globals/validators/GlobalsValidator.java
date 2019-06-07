package com.jba.opencms.web.controller.admin.globals.validators;

import com.jba.opencms.globals.GlobalsService;
import com.jba.opencms.type.system.SystemVariable;
import com.jba.opencms.web.controller.admin.globals.form.SystemVariableForm;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Lazy
public class GlobalsValidator implements ConstraintValidator<UniqueGlobal, SystemVariableForm> {
    private GlobalsService globalsService;

    public GlobalsValidator(GlobalsService globalsService) {
        this.globalsService = globalsService;
    }

    @Override
    public void initialize(UniqueGlobal constraintAnnotation) {
    }

    @Override
    public boolean isValid(SystemVariableForm value, ConstraintValidatorContext context) {
        SystemVariable variable = globalsService.findByKey(value.getKey());
        return variable==null || variable.getId().equals(value.getId());
    }
}
