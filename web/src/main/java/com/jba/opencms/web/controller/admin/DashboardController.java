package com.jba.opencms.web.controller.admin;

import com.jba.opencms.security.SecurityUtils;
import com.jba.opencms.type.user.User;
import com.jba.opencms.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends AbstractController {

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
