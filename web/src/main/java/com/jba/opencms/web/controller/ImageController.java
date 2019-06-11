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
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.AbstractMap.SimpleEntry;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    private final static Map<String, String> mimeTypes;

    static{
        mimeTypes = Stream.of(
                new SimpleEntry<>(".PNG", "image/png"),
                new SimpleEntry<>(".JPG", "image/jpg"),
                new SimpleEntry<>(".JPEG", "image/jpeg"),
                new SimpleEntry<>(".GIF", "image/gif")
        ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }

    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET)
    public RedirectView getImage(
            @PathVariable("imageId") Long imageId,
            HttpServletResponse response
    ){
        response.setStatus(HttpServletResponse.SC_OK);
        return new RedirectView("/image/"+imageId+"/"+imageRepository.getFullName(imageId));
    }

    @RequestMapping(value = "/{imageId}/{imageName}")
    public void getImageWithReadableName(
        @PathVariable("imageId") Long imageId,
        @PathVariable("imageName") String imageName,
        HttpServletResponse response
    ) throws IOException{
        determineContentType(imageRepository.getExtension(imageId), response);
        response.getOutputStream().write(imageRepository.get(imageId));
    }

    private void determineContentType(String ext, HttpServletResponse response){
        response.setContentType(mimeTypes.get(ext));
    }
}
