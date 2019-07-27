package com.jba.opencms.web.controller;

import com.jba.opencms.globals.GlobalsService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class PageController {
    private MenuService menuService;
    private PageService pageService;
    private GlobalsService globalsService;

    public PageController(MenuService menuService, PageService pageService, GlobalsService globalsService) {
        this.menuService = menuService;
        this.pageService = pageService;
        this.globalsService = globalsService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RedirectView home(Model model){
        long homePageId = Long.parseLong(globalsService.findByKey(SystemVariableKeys.PAGE_HOMEPAGE).getValue());

        return new RedirectView("/page?page="+homePageId);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public RedirectView getPage(@RequestParam("page") Long pageId){
        return new RedirectView("/page/"+pageService.findOne(pageId, false).getIdentifier());
    }

    @RequestMapping(value = "/page/{pageName}", method = RequestMethod.GET)
    public String getPage(@PathVariable("pageName") String pageName, Model model, HttpServletResponse response){
        List<Entry> entries = menuService.findActive(true).getEntries();

        Page page = pageService.findByIdentifier(pageName);

        model.addAttribute("page", page);

        model.addAttribute("menu", entries);

        if(page==null||!page.getVisible()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "error/not-found";
        }
        if(page.getPageType()!=null){
            return page.getPageType().getLayoutName();
        }
        else
            return "page-template/one-column-half-width";
    }

}
