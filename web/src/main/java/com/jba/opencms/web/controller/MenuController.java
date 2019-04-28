package com.jba.opencms.web.controller;

import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value = "/dashboard/menu", method = RequestMethod.GET)
    public String mainPage(Model model){
        List<Menu> allMenus = menuDao.findAll();
        allMenus.sort(Comparator.comparing(Menu::getIsActive));
        Collections.reverse(allMenus);
        Menu activeMenu = menuDao.findActiveMenu();
        model.addAttribute("menu", activeMenu.getEntries());
        model.addAttribute("allMenus", allMenus);

        return "dashboard/menu/menu";
    }

    @RequestMapping(value = "/dashboard/menu/new",method = RequestMethod.POST)
    public RedirectView createNewMenu(){
        Menu menu = new Menu();
        menuDao.create(menu);
        return new RedirectView("/dashboard/menu/"+menu.getId());
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}", method = RequestMethod.GET)
    public String getMenuDetails(@PathVariable(name = "menuId") Integer menuId, Model model){
        Menu menu = menuDao.findOne(menuId);

        model.addAttribute("menu", menu);

        return "dashboard/menu/edit";
    }

    @RequestMapping(value = "/dashboard/menu/{menuId", method = RequestMethod.POST)
    public RedirectView postMenuDetails(@PathVariable(name = "menuId") Integer menuId, Menu menu){


        return new RedirectView("/dashboard/menu");
    }
}
