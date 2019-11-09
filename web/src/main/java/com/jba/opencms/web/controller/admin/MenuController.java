package com.jba.opencms.web.controller.admin;

import com.jba.opencms.menu.EntryService;
import com.jba.opencms.menu.MenuService;
import com.jba.opencms.page.PageService;
import com.jba.opencms.type.menu.Entry;
import com.jba.opencms.type.menu.Menu;
import com.jba.opencms.type.page.Page;
import com.jba.opencms.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class MenuController extends AbstractController {

    private MenuService menuService;
    private EntryService entryService;
    private PageService pageService;

    public MenuController(MenuService menuService, EntryService entryService, PageService pageService) {
        this.menuService = menuService;
        this.entryService = entryService;
        this.pageService = pageService;
    }

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
        Menu menu = menuService.findOne(menuId, true);

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
        Menu menu = menuService.findOne(menuId, true);

        Entry entry = new Entry();
        entry.setMenu(menu);
        entry.setLabel("");
        menuService.addMenuEntry(menu, entry);

        return new RedirectView("/dashboard/menu/"+menuId+"/"+entry.getId());
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}/{entryId}", method = RequestMethod.GET)
    public String getMenuEntriesDetails(@PathVariable(name = "menuId") Long menuId,
                                        @PathVariable(name = "entryId") Long entryId,
                                        Model model){
        Entry entry = entryService.findOne(entryId, true);
        model.addAttribute("entry", entry);
        model.addAttribute("subentry", entry.getParent()!=null);

        model.addAttribute("menuId", menuId);
        List<Page> pages = pageService.findAll(true);
        model.addAttribute("pages", pages);

        return "dashboard/menu/entry";
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}/{entryId}", method = RequestMethod.POST)
    public RedirectView postMenuEntriesDetails(@PathVariable(name = "menuId") Long menuId,
                                               @PathVariable(name = "entryId") Long entryId,
                                               String label,
                                               Long page){
        Entry edited = entryService.findOne(entryId, true);

        if(page!=null) {
            edited.setPage(pageService.findOne(page, false));
        }
        edited.setLabel(label);

        entryService.update(edited);

        return new RedirectView("/dashboard/menu/"+menuId +"?success");
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}/{entryId}/new", method = RequestMethod.POST)
    public RedirectView createSubentry(@PathVariable(name = "menuId") Long menuId,
                                               @PathVariable(name = "entryId") Long entryId,
                                               String label,
                                               Long page){
        Entry parentEntry = entryService.findOne(entryId, true);
        parentEntry.setPage(null);
        entryService.update(parentEntry);
        Entry entry = new Entry();
        entry.setLabel("");
        entry.setParent(parentEntry);
        parentEntry.setSubentires(Arrays.asList(entry));
        entryService.create(entry);
        return new RedirectView("/dashboard/menu/"+menuId+"/"+entry.getId());
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}/{entryId}/delete" ,method = RequestMethod.GET)
    public String getDeleteElementForm(@PathVariable(name = "menuId") Long menuId,
                                       @PathVariable(name = "entryId") Long entryId,
                                       Model model){
        Entry entry = entryService.findOne(entryId, true);

        model.addAttribute("menuId", menuId);
        model.addAttribute("entry", entry);

        return "dashboard/menu/delete-entry";
    }

    @RequestMapping(value = "/dashboard/menu/{menuId}/{entryId}/delete" ,method = RequestMethod.POST)
    public RedirectView deleteElement(@PathVariable(name = "menuId") Long menuId,
                                @PathVariable(name = "entryId") Long entryId,
                                Model model){
        entryService.delete(entryId);

        return new RedirectView("/dashboard/menu/"+menuId +"?success");
    }
}
