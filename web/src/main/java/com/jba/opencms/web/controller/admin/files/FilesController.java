package com.jba.opencms.web.controller.admin.files;

import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.projection.FileProjection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/dashboard/files")
@Slf4j
public class FilesController {

    private final static String TO_REMOVE_FROM_PATH = "/dashboard/files/file";

    private FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping
    public String getFiles(Model model){
        List<FileProjection> fileMetadata = fileService.getFileMetadata();

        model.addAttribute("files", fileMetadata);

        return "dashboard/files/files";
    }

    @RequestMapping(value = "file/**")
    public String getFileDetails(HttpServletRequest request, Model model){
        String requestURI = request.getRequestURI();
        String filePath = requestURI.replaceAll(TO_REMOVE_FROM_PATH, "");

        File file = fileService.get(filePath);
        if(file!=null) {
            model.addAttribute("file", file);

            return "dashboard/files/file";
        }
        else return "error/not-found";
    }

    @RequestMapping(value = "file/**", method = RequestMethod.POST)
    public RedirectView updateFile(HttpServletRequest request, File file){
        File fromDB = fileService.findOne(file.getId(), false);
        fromDB.setName(file.getName());
        fromDB.setDescription(file.getDescription());
        fromDB.setPath(file.getPath());

        fileService.update(fromDB);

        return new RedirectView("/dashboard/files?update-success");
    }

    @RequestMapping(value = "/delete")
    public RedirectView deleteFile(@RequestParam("file") Long fileId){
        fileService.delete(fileId);

        return new RedirectView("/dashboard/files?delete-success");
    }
}
