package com.jba.opencms.web.controller.admin;

import com.jba.opencms.file.FileService;
import com.jba.opencms.image.ImageService;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.projection.FileProjection;
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
    private FileService fileService;

    public AdminImageController(ImageService imageService, FileService fileService) {
        this.imageService = imageService;
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getImageDashboard(Model model) {
        List<FileProjection> imagesMetadata = fileService.getImagesMetadata();

        model.addAttribute("images", imagesMetadata);

        return "dashboard/image/images";
    }

    @RequestMapping(value = "/details")
    public String getImageForm(
            @RequestParam("image") String imagePath,
            Model model
    ) {
        File image = fileService.get(imagePath);
        model.addAttribute("image", image);
        model.addAttribute("imageId", image.getId());

        return "dashboard/image/image";
    }

    @RequestMapping(value = "/{imageId}", method = RequestMethod.POST)
    public RedirectView postImage(
            @PathVariable("imageId") Long imageId,
            File image
    ) {
        File imageDB = fileService.findOne(imageId, true);

        imageDB.setName(image.getName());
        if(!image.getDescription().isEmpty())
            imageDB.setDescription(image.getDescription());
        else imageDB.setDescription(null);
        fileService.update(imageDB);

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
