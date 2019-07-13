package com.jba.opencms.web.controller.admin;

import com.jba.opencms.web.repository.FileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/dashboard/presentation")
public class PresentationController {

    private FileRepository repository;

    public PresentationController(FileRepository repository) {
        this.repository = repository;
    }

    @RequestMapping
    public String getPresentation(){
        return "dashboard/presentation/presentation";
    }

    @RequestMapping(value = "/css-edit")
    public String getMainCss(@RequestParam String file){
        if(file.equals("main")){

        }
        else if (file.equals("theme")){

        }

        return "dashboard/presentation/css-edit";
    }
}
