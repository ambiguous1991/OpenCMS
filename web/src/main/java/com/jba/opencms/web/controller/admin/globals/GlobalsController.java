package com.jba.opencms.web.controller.admin.globals;

import com.jba.opencms.globals.GlobalsService;
import com.jba.opencms.type.system.SystemVariable;
import com.jba.opencms.web.controller.admin.globals.form.SystemVariableForm;
import net.bytebuddy.utility.RandomString;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Lazy
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

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public RedirectView createNewGlobal(){
        SystemVariable attribute = new SystemVariable();
        attribute.setKey(RandomString.make(10));
        attribute.setValue("nil");

        globalsService.create(attribute);
        updateGlobals(attribute);

        return new RedirectView("/dashboard/globals/"+attribute.getId());
    }

    @RequestMapping(value = "/{globalId}")
    public String getGlobalEdit(Model model,
                                @PathVariable("globalId") Long globalId){
        SystemVariableForm form = SystemVariableForm.from(globalsService.findOne(globalId, false));
        model.addAttribute("form", form);

        return "dashboard/global/global-edit";
    }

    @RequestMapping(value = "/{globalId}", method = RequestMethod.POST)
    public RedirectView postChanges(@Valid SystemVariableForm attribute,
                                    BindingResult bindingResult,
                                    @PathVariable("globalId") Long globalId){
        if(bindingResult.hasErrors()){
            RedirectView redirectView = new RedirectView("/dashboard/globals/{globalId}");
            redirectView.setStatusCode(HttpStatus.BAD_REQUEST);
            return redirectView;
        }

        SystemVariable fromDB = globalsService.findOne(globalId, false);
        fromDB.setKey(attribute.getKey());
        fromDB.setValue(attribute.getValue());

        globalsService.update(fromDB);
        updateGlobals(fromDB);

        return new RedirectView("/dashboard/globals?success");
    }

    private void updateGlobals(SystemVariable systemVariable){
        if(systemVariables.containsKey(systemVariable.key)) {
            systemVariables.remove(systemVariable.key);
        }
        systemVariables.put(systemVariable.key, systemVariable.value);
    }
}
