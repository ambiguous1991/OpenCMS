package com.jba.opencms.web.controller.admin.users.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsernamePasswordForm {
    private String username;
    private String password;
}
