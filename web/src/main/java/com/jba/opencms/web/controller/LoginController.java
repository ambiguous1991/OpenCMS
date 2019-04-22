package com.jba.opencms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(){
        return "login-page";
    }

    @GetMapping("/logout")
    public RedirectView redirectView(){
        return new RedirectView("/");
    }
}
