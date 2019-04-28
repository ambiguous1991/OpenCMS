package com.jba.opencms.web.controller;

import com.jba.opencms.menu.MenuService;
import com.jba.opencms.type.menu.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        List<Entry> entries = menuService.findActive(true).getEntries();

        model.addAttribute("menu", entries);

        return "index";
    }

}
