package com.jba.opencms.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/dashboard/presentation")
public class PresentationController {

    @RequestMapping
    public String getPresentation(){
        return "dashboard/presentation/presentation";
    }
}
