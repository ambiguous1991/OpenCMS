package com.jba.opencms.web.controller.admin.users.form;

import com.jba.opencms.web.controller.admin.users.validators.PasswordEquals;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@PasswordEquals
public class ChangePasswordForm {
    @NotNull private String username;
    @NotNull private String password;
    @NotNull private String newPassword;
    @NotNull private String newPasswordRepeated;

    public ChangePasswordForm(@NotNull String username) {
        this.username = username;
    }
}
