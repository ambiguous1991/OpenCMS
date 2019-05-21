package com.jba.opencms.web.repository;

import com.jba.opencms.image.ImageService;
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
}
