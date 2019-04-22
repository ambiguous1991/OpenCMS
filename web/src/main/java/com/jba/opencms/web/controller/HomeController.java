package com.jba.opencms.web.controller;

import com.jba.opencms.dao.GenericDao;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import com.jba.opencms.type.menu.MenuEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(GenericDao<Menu> menuDao, Model model){
        List<Menu> all = menuDao.findAll();

        Menu menuContainer = all.get(0);
        List<Entry> menu = menuContainer.getMenuEntryList().stream()
                .filter(MenuEntry::getIsActive)
                .map(MenuEntry::getEntry)
                .collect(Collectors.toList());

        model.addAttribute("menu", menu);

        return "index";
    }

}
