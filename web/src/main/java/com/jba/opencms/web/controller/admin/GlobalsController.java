package com.jba.opencms.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/dashboard/globals")
public class GlobalsController {

    @RequestMapping
    public String getGlobalsList(){
        return "dashboard/global/globals";
    }
}
