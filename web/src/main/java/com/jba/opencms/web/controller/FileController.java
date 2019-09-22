package com.jba.opencms.web.controller;

import com.amazonaws.util.IOUtils;
import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addFile(){

    }

    @RequestMapping(method = RequestMethod.GET)
    public void getFile(
            HttpServletResponse response,
            @RequestParam(name = "path") String path) throws IOException {
        File file = fileService.get(path);
        response.setContentType(file.getMime());
        IOUtils.copy(new ByteArrayInputStream(file.getData()), response.getOutputStream());
    }
}
