package com.jba.opencms.web.repository;

import com.jba.opencms.image.ImageService;
import com.jba.opencms.type.image.Image;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

    private ImageService imageService;

    public ImageRepositoryImpl(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public byte[] get(Long id) {
        return imageService.findOne(id, false).getData();
    }

    @Override
    public String getName(Long id) {
        return imageService.findOne(id, false).getName();
    }

    @Override
    public String getExtension(Long id) {
        return imageService.findOne(id, false).getExtension();
    }

    @Override
    public String getFullName(Long id) {
        Image image = imageService.findOne(id, false);
        return image.getName()+"."+image.getExtension();
    }
}
