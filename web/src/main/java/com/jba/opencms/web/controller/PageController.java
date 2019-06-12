package com.jba.opencms.web.controller;

import com.jba.opencms.menu.MenuService;
import com.jba.opencms.page.PageService;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.page.Page;
import com.jba.opencms.type.system.enu.SystemVariableKeys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class PageController {
    private MenuService menuService;
    private PageService pageService;
    private Map<String, String> systemVariables;

    public PageController(MenuService menuService, PageService pageService, Map<String, String> systemVariables) {
        this.menuService = menuService;
        this.pageService = pageService;
        this.systemVariables = systemVariables;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        List<Entry> entries = menuService.findActive(true).getEntries();
        Long homePageId = Long.parseLong(systemVariables.get(SystemVariableKeys.PAGE_HOMEPAGE));

        Page page = pageService.findOne(homePageId, true);

        model.addAttribute("page", page);

        model.addAttribute("menu", entries);

        return "page-template/one-column-half-width";
    }

    @RequestMapping(value = "/page/{pageId}", method = RequestMethod.GET)
    public String getPage(@PathVariable("pageId") Long pageId, Model model, HttpServletResponse response){
        List<Entry> entries = menuService.findActive(true).getEntries();

        Page page = pageService.findOne(pageId, true);

        model.addAttribute("page", page);

        model.addAttribute("menu", entries);

        if(page==null||!page.getVisible()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "error/404";
        }

        if(page.getPageType()!=null){
            return page.getPageType().getLayoutName();
        }
        else
            return "page-template/one-column-half-width";
    }

}
