package com.jba.opencms.web.controller.admin.users;

import com.jba.opencms.security.SecurityUtils;
import com.jba.opencms.type.user.User;
import com.jba.opencms.web.controller.admin.users.form.ChangePasswordForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/dashboard/users")
public class UsersController {

    @RequestMapping
    public String getLandingPage(){
        return "dashboard/users/users";
    }

    @RequestMapping(value = "/change-password")
    public String getPasswordChangeForm(HttpServletRequest request, Model model){
        User userFromRequest = SecurityUtils.getUserFromRequest(request.getUserPrincipal());

        ChangePasswordForm form = new ChangePasswordForm();

        model.addAttribute("user", userFromRequest);
        model.addAttribute("form", form);

        return "dashboard/users/change-password";
    }
}
