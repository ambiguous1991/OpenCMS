package com.jba.opencms.web.controller.admin;

import com.jba.opencms.image.ImageService;
import com.jba.opencms.type.image.Image;
import com.mysql.cj.jdbc.Blob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping(value = "/dashboard/images")
public class AdminImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public String getImageDashboard() {
        return "dashboard/image/images";
    }

    @RequestMapping(value = "/add")
    public String getNewImageForm() {
        return "dashboard/image/add";
    }

    @RequestMapping(value = "/{imageId}/upload", method = RequestMethod.PUT)
    public ResponseEntity upload(
            @PathVariable("imageId") Long imageId,
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        file.toString();

        Image image = new Image();
        try {
            image.setData(file.getBytes());
            image.setExtension(file.getOriginalFilename());
            String originalFilename = file.getOriginalFilename()==null?"":file.getOriginalFilename();
            if(originalFilename.contains(".")){
                String extension = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
                String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
                image.setExtension(extension.toUpperCase());
                image.setName(title);
            }
            else{
                image.setExtension("NIL");
                image.setName("none");
            }
            imageService.create(image);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
