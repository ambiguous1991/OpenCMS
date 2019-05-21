package com.jba.opencms.web.controller;

import com.jba.opencms.web.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
    public void getImage(
            @PathVariable("imageId") Long imageId,
            HttpServletResponse response
    ) throws IOException {
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(imageRepository.get(imageId));
    }
}
