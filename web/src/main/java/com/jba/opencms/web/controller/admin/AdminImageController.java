package com.jba.opencms.web.controller.admin;

import com.jba.opencms.file.FileService;
import com.jba.opencms.type.file.File;
import com.jba.opencms.type.file.projection.FileProjection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/dashboard/images")
public class AdminImageController {

    private FileService fileService;

    public AdminImageController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getImageDashboard(Model model) {
        List<FileProjection> images = fileService.getImagesMetadata();

        model.addAttribute("images", images);

        return "dashboard/image/images";
    }

    @RequestMapping(value = "/details/**")
    public String getImageForm(
            HttpServletRequest request,
            Model model
    ) {
        String requestURI = request.getRequestURI().replace("/dashboard/images/details", "");

        File image = fileService.get(requestURI);
        if(image!=null) {
            model.addAttribute("image", image);
            model.addAttribute("imageId", image.getId());

            return "dashboard/image/image";
        }
        else return "redirect:/404";
    }

    @RequestMapping(value = "/{imageId}", method = RequestMethod.POST)
    public RedirectView postImage(
            @PathVariable("imageId") Long imageId,
            File image
    ) {
        File imageDB = fileService.findOne(imageId, false);

        imageDB.setName(image.getName());
        imageDB.setDescription(image.getDescription());
        fileService.update(imageDB);

        return new RedirectView("/dashboard/images?success");
    }

    @RequestMapping(value = "/{imageId}/delete", method = RequestMethod.GET)
    public RedirectView deleteImage(
        @PathVariable("imageId") Long imageId
    ){
        fileService.delete(imageId);

        return new RedirectView("/dashboard/images?success");
    }
}
