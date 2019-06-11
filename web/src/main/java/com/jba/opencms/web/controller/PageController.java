package com.jba.opencms.web.controller;

import com.jba.opencms.menu.MenuService;
import com.jba.opencms.page.PageService;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.page.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PageController {
    private MenuService menuService;
    private PageService pageService;

    public PageController(MenuService menuService, PageService pageService) {
        this.menuService = menuService;
        this.pageService = pageService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        List<Entry> entries = menuService.findActive(true).getEntries();

        Page page = pageService.findOne(1L, true);

        model.addAttribute("page", page);

        model.addAttribute("menu", entries);

        return "page-template/one-column-half-width";
    }

    @RequestMapping(value = "/page/{pageId}", method = RequestMethod.GET)
    public String getPage(@PathVariable("pageId") Long pageId, Model model){
        List<Entry> entries = menuService.findActive(true).getEntries();

        Page page = pageService.findOne(pageId, true);

        model.addAttribute("page", page);

        model.addAttribute("menu", entries);

        return "index";
    }

}
