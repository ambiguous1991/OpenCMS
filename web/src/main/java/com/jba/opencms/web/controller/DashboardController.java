package com.jba.opencms.web.controller;

import com.jba.opencms.security.SecurityUtils;
import com.jba.opencms.security.principal.UserPrincipal;
import com.jba.opencms.type.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/me")
    public String me(HttpServletRequest request, Model model){
        User user = SecurityUtils.getUserFromRequest(request.getUserPrincipal());

        model.addAttribute("user", user);
        return "dashboard/me";
    }

}
