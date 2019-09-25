package com.jba.opencms.web.controller;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.File;
import com.jba.opencms.web.form.file.FileUploadForm;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ModelAttribute
    public FileUploadForm getFileUploadForm(HttpServletRequest request){
        try {
            return new FileUploadForm((StandardMultipartHttpServletRequest)request);
        }
        catch (Exception e){
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addFile(@ModelAttribute FileUploadForm form) throws IOException{
        Map<String, String> result = new HashMap<>();

        try {
            if (!fileService.exists(form.getFilePath())) {
                File file = new File();
                file.setData(form.getMultipart().getBytes());
                file.setName(form.getFileName());
                file.setMime(form.getMultipart().getContentType());
                file.setPath(form.getFilePath());

                fileService.create(file);
                result.put("result", "success");
                result.put("path", "/dashboard/presentation?success&file=" + file.getPath());
                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(result), HttpStatus.OK);
            }
        }
        catch (NonUniqueResultException e){
            log.error(String.format("INCONSISTENT database - %s - filename: %s", e.getMessage(), form.getFilePath()));
        }
        result.put("result", "error");
        result.put("path", "/dashboard/presentation?error&message=Plik o podanej nazwie już istnieje");
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(result), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void getFile(
            HttpServletResponse response,
            @RequestParam(name = "path") String path) throws IOException {
        File file = fileService.get(path);
        if(file!=null) {
            response.setContentType(file.getMime());
            IOUtils.copy(new ByteArrayInputStream(file.getData()), response.getOutputStream());
        }
        else response.sendRedirect("/404");
    }
}
