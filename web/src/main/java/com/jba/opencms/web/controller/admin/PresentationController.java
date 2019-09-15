package com.jba.opencms.web.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jba.opencms.web.repository.FileAccessMode;
import com.jba.opencms.web.repository.FileRepository;
import com.jba.opencms.web.utils.ContentType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static com.jba.opencms.web.repository.FileAccessMode.PUBLIC_READ_ONLY;
import static com.jba.opencms.web.utils.ContentType.TEXT_CSS;
import static com.jba.opencms.web.utils.ContentType.TEXT_JAVASCRIPT;

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

    @RequestMapping(method= RequestMethod.PUT, value = "/edit-css")
    public ResponseEntity update(@RequestParam String file, String content){
        repository.save(file, new ByteArrayInputStream(content.getBytes()), PUBLIC_READ_ONLY, TEXT_CSS);

        return ResponseEntity.ok("{\n\t\"status\":\"ok\"\n}");
    }

    @RequestMapping(method= RequestMethod.POST, value = "/file")
    public ResponseEntity<String> postNewFile( @RequestParam("file") MultipartFile file) throws IOException{
        Map<String, String> result = new HashMap<>();
        if(file.getContentType()!=null) {
            if (file.getContentType().equals(TEXT_CSS)) {
                repository.save("/resources/css/"+file.getOriginalFilename(), file.getInputStream(), PUBLIC_READ_ONLY, file.getContentType());
                result.put("result","success");
                result.put("path", "/dashboard/presentation?success&file="+file.getOriginalFilename());
                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(result), HttpStatus.OK);
            }
            else if (file.getContentType().equals(TEXT_JAVASCRIPT)) {
                repository.save("/resources/js/"+file.getName(), file.getInputStream(), PUBLIC_READ_ONLY, file.getContentType());
                result.put("result","success");
                result.put("path", "/dashboard/presentation?success&file="+file.getOriginalFilename());
                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(result), HttpStatus.OK);
            }
        }
        result.put("result", "fail");
        result.put("message", "File type unrecognized!");
        result.put("path", "/dashboard/presentation?error&message=File type unrecognized");
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(result), HttpStatus.OK);
    }
}
