package com.jba.opencms.web.controller.admin;

import com.jba.opencms.web.repository.FileRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller
@RequestMapping(value = "/dashboard/presentation")
public class PresentationController {

    private FileRepository repository;

    public PresentationController(@Qualifier(value = "fileRepository") FileRepository repository) {
        this.repository = repository;
    }

    @RequestMapping
    public String getPresentation(){
        return "dashboard/presentation/presentation";
    }

    @RequestMapping(value = "/edit-css")
    public String getMainCss(@RequestParam String file, Model model) throws IOException {
        InputStream inputStream = repository.get(file);
        String content = FileCopyUtils.copyToString(new InputStreamReader(inputStream));
        model.addAttribute("content", content);
        return "dashboard/presentation/edit-css";
    }
}
