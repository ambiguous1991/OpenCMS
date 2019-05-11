package com.jba.opencms.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard/page")
public class PageController {

    @RequestMapping(method = RequestMethod.GET)
    public String getLandingPage(){
        return "dashboard/page/pages";
    }
}
