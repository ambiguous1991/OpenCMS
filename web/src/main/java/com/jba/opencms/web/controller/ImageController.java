package com.jba.opencms.web.controller;

import com.jba.opencms.web.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
    public RedirectView getImage(
            @PathVariable("imageId") Long imageId,
            HttpServletResponse response
    ) throws IOException {
        return new RedirectView("/image/"+imageId+"/"+imageRepository.getFullName(imageId));
    }

    @RequestMapping(value = "/{imageId}/{imageName}")
    public void getImageWithReadableName(
        @PathVariable("imageId") Long imageId,
        @PathVariable("imageName") String imageName,
        HttpServletResponse response
    ) throws IOException{
        switch (imageRepository.getExtension(imageId)){
            case ".PNG": {
                response.setContentType("image/png");
                break;
            }
            case ".JPG": {
                response.setContentType("image/jpg");
                break;
            }
            case ".JPEG": {
                response.setContentType("image/jpeg");
                break;
            }
            case ".GIF": {
                response.setContentType("image/gif");
                break;
            }

        }
        response.getOutputStream().write(imageRepository.get(imageId));
    }
}
