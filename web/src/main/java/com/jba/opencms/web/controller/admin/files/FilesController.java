package com.jba.opencms.web.controller.admin.files;

import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.projection.FileProjection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/dashboard/files")
public class FilesController {

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
}
