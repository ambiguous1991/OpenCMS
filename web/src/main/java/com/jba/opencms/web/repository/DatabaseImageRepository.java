package com.jba.opencms.web.repository;

import com.jba.opencms.image.ImageService;
import com.jba.opencms.type.image.Image;

public class DatabaseImageRepository implements ImageRepository {

    private ImageService imageService;

    public DatabaseImageRepository(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public byte[] get(String id) {
        return imageService.findOne(Long.parseLong(id), false).getData();
    }

    @Override
    public String getName(String id) {
        return imageService.findOne(Long.parseLong(id), false).getName();
    }

    @Override
    public String getExtension(String id) {
        return imageService.findOne(Long.parseLong(id), false).getExtension();
    }

    @Override
    public String getFullName(String id) {
        Image image = imageService.findOne(Long.parseLong(id), false);
        return image.getName()+"."+image.getExtension();
    }
}
