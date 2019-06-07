package com.jba.opencms.web.controller.admin;

import com.jba.opencms.globals.GlobalsService;
import com.jba.opencms.type.system.SystemVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@RequestMapping(value = "/dashboard/globals")
public class GlobalsController {

    private GlobalsService globalsService;
    private Map<String, String> systemVariables;

    public GlobalsController(GlobalsService globalsService, Map<String, String> systemVariables) {
        this.globalsService = globalsService;
        this.systemVariables = systemVariables;
    }

    @RequestMapping
    public String getGlobalsList(Model model){
        model.addAttribute("attributes", globalsService.findAll(false));

        return "dashboard/global/globals";
    }

    @RequestMapping(value = "/{globalId}")
    public String getGlobalEdit(Model model,
                                @PathVariable("globalId") Long globalId){
        model.addAttribute("attribute", globalsService.findOne(globalId, false));

        return "dashboard/global/global-edit";
    }

    @RequestMapping(value = "/{globalId}", method = RequestMethod.POST)
    public RedirectView postChanges(SystemVariable attribute,
                                    @PathVariable("globalId") Long globalId){
        SystemVariable fromDB = globalsService.findOne(globalId, false);
        fromDB.setKey(attribute.key);
        fromDB.setValue(attribute.value);

        globalsService.update(fromDB);
        systemVariables.remove(attribute.key);
        systemVariables.put(attribute.key, attribute.value);

        return new RedirectView("/dashboard/globals?success");
    }
}
