package com.jba.opencms.web.controller.admin;

import com.jba.opencms.globals.GlobalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/dashboard/globals")
public class GlobalsController {

    @Autowired GlobalsService globalsService;

    @RequestMapping
    public String getGlobalsList(Model model){
        model.addAttribute("attributes", globalsService.findAll(false));

        return "dashboard/global/globals";
    }
}
