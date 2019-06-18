package com.jba.opencms.web.controller.admin.users;

import com.jba.opencms.security.SecurityUtils;
import com.jba.opencms.type.user.User;
import com.jba.opencms.web.controller.admin.users.form.ChangePasswordForm;
import com.jba.opencms.web.controller.admin.users.form.UsernamePasswordForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/dashboard/users")
public class UsersController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    public UsersController(PasswordEncoder passwordEncoder, @Qualifier("userDetailsProvider") UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping
    public String getLandingPage(){
        return "dashboard/users/users";
    }

    @RequestMapping(value = "/change-password")
    public String getPasswordChangeForm(HttpServletRequest request, Model model){
        User userFromRequest = SecurityUtils.getUserFromRequest(request.getUserPrincipal());
        ChangePasswordForm form;
        if(userFromRequest!=null) {
            form = new ChangePasswordForm(userFromRequest.getEmail());
        }
        else
            form = new ChangePasswordForm();
        model.addAttribute("form", form);

        return "dashboard/users/change-password";
    }

    @RequestMapping(value = "/change-password-validate", method = RequestMethod.PATCH)
    public ResponseEntity<Boolean> isPasswordCorrect(@RequestBody UsernamePasswordForm form){
        UserDetails userDetails = userDetailsService.loadUserByUsername(form.getUsername());

        return new ResponseEntity<>(
                passwordEncoder.matches(form.getPassword(),userDetails.getPassword()),
                HttpStatus.OK
        );
    }
}
