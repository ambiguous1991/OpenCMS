package com.jba.opencms.web.controller;

import com.jba.opencms.menu.MenuService;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/dashboard/menu", method = RequestMethod.GET)
    public String mainPage(Model model){
        List<Menu> allMenus = menuService.findAll(true);
        allMenus.sort(Comparator.comparing(Menu::getIsActive));
        Collections.reverse(allMenus);
        Menu activeMenu = menuService.findActive(true);
        model.addAttribute("menu", activeMenu.getEntries());
        model.addAttribute("allMenus", allMenus);

        return "dashboard/menu/menu";
    }

    @RequestMapping(value = "/dashboard/menu/new",method = RequestMethod.POST)
    public RedirectView createNewMenu(){
        Menu menu = new Menu();
        menuService.create(menu);
        return new RedirectView("/dashboard/menu/"+menu.getId());
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}", method = RequestMethod.GET)
    public String getMenuDetails(@PathVariable(name = "menuId") Long menuId, Model model){
        Menu menu = menuService.findOne(menuId, false);

        model.addAttribute("menu", menu);

        return "dashboard/menu/edit";
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}", method = RequestMethod.POST)
    public RedirectView postMenuDetails(@PathVariable(name = "menuId") Long menuId, Menu menu){
        Menu edited = menuService.findOne(menuId, true);

        edited.setIsActive(menu.getIsActive());
        edited.setName(menu.getName());

        menuService.update(edited);

        return new RedirectView("/dashboard/menu");
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}/new", method = RequestMethod.POST)
    public RedirectView createNewMenuEntry(@PathVariable(name = "menuId") Long menuId){
        Menu menu = menuService.findOne(menuId, false);

        Entry entry = new Entry();
        entry.setMenu(menu);
        entry.setLabel("");
        menuService.addMenuEntry(menu, entry);

        return new RedirectView("redirect:/dashboard/menu/"+menuId+"/"+entry.getId());
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}/{entryId}", method = RequestMethod.GET)
    public String getMenuEntriesDetails(@PathVariable(name = "menuId") Long menuId,
                                        @PathVariable(name = "entryId") Long entryId,
                                        Model model){
        Menu menu = menuService.findOne(menuId, true);

        model.addAttribute("entry", menu.getEntries().stream()
                .filter(entry -> entry.getId().equals(entryId))
                .findAny().orElse(null)
        );

        return "dashboard/menu/entry";
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}/{entryId}", method = RequestMethod.POST)
    public RedirectView postMenuEntriesDetails(@PathVariable(name = "menuId") Long menuId, Model model){
        Menu menu = menuService.findOne(menuId, false);

        model.addAttribute("menu", menu);

        return new RedirectView("/dashboard/menu/"+menu.getId());
    }
}
