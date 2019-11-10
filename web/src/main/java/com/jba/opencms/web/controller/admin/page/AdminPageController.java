package com.jba.opencms.web.controller.admin.page;

import com.jba.opencms.file.FileService;
import com.jba.opencms.menu.MenuService;
import com.jba.opencms.page.PageService;
import com.jba.opencms.page.PageTypeService;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.page.Page;
import com.jba.opencms.web.controller.AbstractController;
import com.jba.opencms.web.form.resource.ResourceForm;
import com.jba.opencms.web.message.AbstractConverter;
import com.jba.opencms.web.type.resource.ScriptToResourceWrapperConverter;
import com.jba.opencms.web.type.resource.StylesheetToResourceWrapperConverter;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/dashboard/page")
public class AdminPageController extends AbstractController {

    private PageService pageService;
    private MenuService menuService;
    private PageTypeService pageTypeService;
    private FileService fileService;
    private AbstractConverter<File> acceptedScripts, rejectedScripts;
    private AbstractConverter<File> acceptedStylesheets, rejectedStylesheets;

    public AdminPageController(PageService pageService,
                               MenuService menuService,
                               PageTypeService pageTypeService,
                               FileService fileService,
                               AbstractConverter<File> scriptAcceptedConverter,
                               AbstractConverter<File> scriptRejectedConverter,
                               AbstractConverter<File> stylesheetAcceptedConverter,
                               AbstractConverter<File> stylesheetRejectedConverter)
    {
        this.pageService = pageService;
        this.menuService = menuService;
        this.pageTypeService = pageTypeService;
        this.fileService = fileService;
        this.acceptedScripts = scriptAcceptedConverter;
        this.rejectedScripts = scriptRejectedConverter;
        this.acceptedStylesheets = stylesheetAcceptedConverter;
        this.rejectedStylesheets = stylesheetRejectedConverter;
    }

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
        if (!pageService.identifierAvailable("new-page", -1L)) {
            page.setIdentifier("new-page-" + RandomString.make(10));
        } else {
            page.setIdentifier("new-page");
        }
        page.setVisible(false);
        page.setIsMobileEnabled(true);

        pageService.create(page);

        return new RedirectView("/dashboard/page/" + page.getId());
    }

    @RequestMapping(value = "/{pageId}", method = RequestMethod.GET)
    public String getPageDetails(
            @PathVariable("pageId") Long pageId,
            Model model) {
        Page page = pageService.findOne(pageId, true);

        model.addAttribute("page", page);

        List<PageType> pageTypes = pageTypeService.findAll(false);
        model.addAttribute("pageTypes", pageTypes);

        return "dashboard/page/page-details";
    }

    @RequestMapping(value = "/{pageId}", method = RequestMethod.POST)
    public RedirectView updatePageDetails(
            @PathVariable("pageId") Long pageId,
            String title, Long pageType, String identifier,
            Boolean visible, Boolean isMobileEnabled) {
        Page page = pageService.findOne(pageId, true);
        page.setTitle(title);
        if (!page.getIdentifier().equals(identifier) && !pageService.identifierAvailable(identifier, pageId)) {
            identifier += "-" + pageId;
        }
        page.setIdentifier(identifier);
        if (visible != null) {
            page.setVisible(visible);
        } else page.setVisible(false);
        if (isMobileEnabled != null) {
            page.setIsMobileEnabled(isMobileEnabled);
        } else page.setIsMobileEnabled(false);

        PageType selectedPageType = pageTypeService.findOne(pageType, false);
        page.setPageType(selectedPageType);

        pageService.update(page);

        return new RedirectView("/dashboard/page");
    }

    @RequestMapping(value = "/{pageId}/scripts", method = RequestMethod.GET)
    public String getPageScripts(@PathVariable("pageId") Long pageId, Model model, ScriptToResourceWrapperConverter converter) {
        Page page = pageService.findOne(pageId, true);

        List<File> availableScripts = fileService.findAll(Collections.singletonList("text/javascript"));
        List<File> pageScripts = page.getScripts();
        availableScripts.removeAll(pageScripts);

        ResourceForm form = new ResourceForm(converter.convert(availableScripts), converter.convert(pageScripts));

        model.addAttribute("form", form);
        model.addAttribute("page", page);
        model.addAttribute("RESOURCE_TYPE", true);

        return "dashboard/page/resources";
    }

    @RequestMapping(value = "/{pageId}/stylesheets", method = RequestMethod.GET)
    public String getStylesheets(@PathVariable("pageId") Long pageId, Model model, StylesheetToResourceWrapperConverter converter) {
        Page page = pageService.findOne(pageId, true);

        List<File> availableStylesheets = fileService.findAll(Collections.singletonList("text/css"));
        List<File> pageStylesheets = page.getStylesheets();
        availableStylesheets.removeAll(pageStylesheets);

        ResourceForm form = new ResourceForm(converter.convert(availableStylesheets), converter.convert(pageStylesheets));

        model.addAttribute("form", form);
        model.addAttribute("page", page);
        model.addAttribute("RESOURCE_TYPE", false);

        return "dashboard/page/resources";
    }

    @RequestMapping(value = "/{pageId}/scripts", method = RequestMethod.POST)
    public RedirectView addPageScripts(@PathVariable("pageId") Long pageId, @RequestBody String body) {
        Page page = pageService.findOne(pageId, true);

        List<File> toAdd = acceptedScripts.read(body);
        List<File> toRemove = rejectedScripts.read(body);

        page.getScripts().removeAll(toRemove);
        page.getScripts().addAll(toAdd);

        pageService.update(page);
        return new RedirectView("/dashboard/page/" + pageId + "?success");
    }

    @RequestMapping(value = "/{pageId}/stylesheets", method = RequestMethod.POST)
    public RedirectView addPageStylesheets(@PathVariable("pageId") Long pageId, @RequestBody String body) {
        Page page = pageService.findOne(pageId, true);

        List<File> toAdd = acceptedStylesheets.read(body);
        List<File> toRemove = rejectedStylesheets.read(body);

        page.getStylesheets().removeAll(toRemove);
        page.getStylesheets().addAll(toAdd);

        pageService.update(page);
        return new RedirectView("/dashboard/page/" + pageId + "?success");
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

        if (page.getPageType() != null) {
            return page.getPageType().getLayoutName();
        } else
            return "page-template/one-column-half-width";
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
