package com.jba.opencms.web.controller;

import com.jba.opencms.dao.ifs.MenuDao;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import com.jba.opencms.type.menu.MenuEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/dashboard/menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @GetMapping
    public String mainPage(Model model){
        List<Menu> allMenus = menuDao.findAll();
        allMenus.sort(Comparator.comparing(Menu::getIsActive));
        Collections.reverse(allMenus);
        Menu activeMenu = menuDao.findActiveMenu();
        List<Entry> menuEntries = activeMenu.getMenuEntryList().stream().map(MenuEntry::getEntry).collect(Collectors.toList());
        model.addAttribute("menu", menuEntries);
        model.addAttribute("allMenus", allMenus);

        return "dashboard/menu";
    }

    @GetMapping("/{menuId}")
    public String getMenuDetails(@PathVariable(name = "menuId") Integer menuId){
        Menu one = menuDao.findOne(menuId);

        return "ok";
    }
}
