package com.jba.opencms.web.controller.admin.users.form;

import com.jba.opencms.web.controller.admin.users.validators.PasswordEquals;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordEquals
public class ChangePasswordForm {
    @NotNull private String password;
    @NotNull private String newPassword;
    @NotNull private String newPasswordRepeated;
}
