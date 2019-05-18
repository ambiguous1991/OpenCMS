package com.jba.opencms.web.controller.admin;

import com.jba.opencms.menu.MenuService;
import com.jba.opencms.page.PageService;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/dashboard/page")
public class PageController {

    @Autowired private PageService pageService;
    @Autowired private MenuService menuService;

    @RequestMapping(method = RequestMethod.GET)
    public String getLandingPage(Model model) {
        List<Page> pages = pageService.findAll(true);

        model.addAttribute("pages", pages);

        return "dashboard/page/pages";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public RedirectView createNewPage() {
        Page page = new Page();

        page.setTitle("New page");
        page.setVisible(false);

        pageService.create(page);

        return new RedirectView("/dashboard/page/" + page.getId());
    }

    @RequestMapping(value = "/{pageId}", method = RequestMethod.GET)
    public String getPageDetails(
            @PathVariable("pageId") Long pageId,
            Model model) {
        Page page = pageService.findOne(pageId, true);

        model.addAttribute("page", page);

        return "dashboard/page/page-details";
    }

    @RequestMapping(value = "/{pageId}/edit", method = RequestMethod.GET)
    public String editPage(
            @PathVariable("pageId") Long pageId,
            Model model) {
        Page page = pageService.findOne(pageId, true);

        model.addAttribute("page", page);

        return "dashboard/page/page-source";
    }

    @RequestMapping(value = "/{pageId}/preview", method = RequestMethod.GET)
    public String previewPage(
            @PathVariable("pageId") Long pageId,
            Model model) {
        Page page = pageService.findOne(pageId, true);
        List<Entry> menu = menuService.findActive(true).getEntries();

        model.addAttribute("page", page);
        model.addAttribute("menu", menu);

        return "index";
    }

    @RequestMapping(value = "/{pageId}/update", method = RequestMethod.PUT)
    @Async
    public ResponseEntity result(@PathVariable("pageId") Long pageId,
                                 String content) {
        pageService.updateContents(pageId, content);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{pageId}/delete", method = RequestMethod.GET)
    public String deletePage(
            @PathVariable("pageId") Long pageId,
            Model model) {
        Page page = pageService.findOne(pageId, true);

        model.addAttribute("page", page);

        return "dashboard/page/delete-page";
    }

    @RequestMapping(value = "/{pageId}/delete", method = RequestMethod.POST)
    public RedirectView doDeletePage(
            @PathVariable("pageId") Long pageId) {
        pageService.delete(pageId);
        return new RedirectView("/dashboard/page?deleteSuccess");
    }
}
