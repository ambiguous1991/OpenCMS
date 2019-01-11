package com.jba.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class Admin {

    @RequestMapping
    public String admin(){
        return "admin";
    }

    @RequestMapping("/dashboard")
    public String dashboard() {return "admin-dashboard";}
}
