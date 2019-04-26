package com.jba.opencms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/dashboard/menu")
public class MenuController {

    @GetMapping
    public String mainPage(){
        return "dashboard/menu";
    }
}
