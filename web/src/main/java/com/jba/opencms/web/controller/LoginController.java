package com.jba.opencms.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends AbstractController {

    @GetMapping("/login")
    public String loginForm(){
        return "login-page";
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            securityContextLogoutHandler.setInvalidateHttpSession(true);
            securityContextLogoutHandler.setClearAuthentication(true);
            securityContextLogoutHandler.logout(request, null, null);
        }
        return new RedirectView("/");
    }
}
