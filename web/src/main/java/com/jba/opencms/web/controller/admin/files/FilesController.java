package com.jba.opencms.web.controller.admin.files;

import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.projection.FileProjection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("file", file);

        log.info(request.getRequestURI());
        return "error/not-found";
    }
}
