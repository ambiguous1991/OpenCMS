package com.jba.opencms.web.controller.admin;

import com.jba.opencms.image.ImageService;
import com.jba.opencms.type.image.Image;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/dashboard/images")
public class AdminImageController {

    private ImageService imageService;

    public AdminImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getImageDashboard(Model model) {
        List<Image> images = imageService.findAll(true);

        model.addAttribute("images", images);

        return "dashboard/image/images";
    }

    @RequestMapping(value = "/{imageId}")
    public String getImageForm(
            @PathVariable("imageId") Long imageId,
            Model model
    ) {
        Image image = imageService.findOne(imageId, true);
        model.addAttribute("image", image);
        model.addAttribute("imageId", image.getId());

        return "dashboard/image/image";
    }

    @RequestMapping(value = "/{imageId}", method = RequestMethod.POST)
    public RedirectView postImage(
            @PathVariable("imageId") Long imageId,
            Image image
    ) {
        Image imageDB = imageService.findOne(imageId, false);

        imageDB.setName(image.getName());
        imageDB.setDescription(image.getDescription());
        imageService.update(imageDB);

        return new RedirectView("/dashboard/images?success");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.PUT)
    public ResponseEntity upload(
            @RequestParam("file") MultipartFile file) {
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("/dashboard/images/"+image.getId(), HttpStatus.OK);
    }
}
