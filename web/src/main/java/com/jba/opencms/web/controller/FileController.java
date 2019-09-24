package com.jba.opencms.web.controller;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.File;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addFile(@RequestParam(name = "file") MultipartFile multipart, String fileNameData) throws IOException{
        Map<String, String> result = new HashMap<>();

        File file = new File();
        file.setData(multipart.getBytes());
        file.setName(fileNameData);
        file.setMime(multipart.getContentType());
        file.setPath("/resources/"+fileNameData);

        fileService.create(file);
        result.put("result","success");
        result.put("path", "/dashboard/presentation?success&file="+file.getPath());
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(result), HttpStatus.OK);
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
