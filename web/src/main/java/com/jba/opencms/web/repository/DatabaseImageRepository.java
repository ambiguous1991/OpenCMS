package com.jba.opencms.web.repository;

import com.jba.opencms.file.FileService;

public class DatabaseImageRepository implements ImageRepository {

    private FileService fileService;

    public DatabaseImageRepository(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public byte[] get(String path) {
        return fileService.get(path).getData();
    }

    @Override
    public String getName(String path) {
        return fileService.get(path).getName();
    }

    @Override
    public String getExtension(String path) {
        return fileService.get(path).getMime();
    }

    @Override
    public String getFullName(String id) {
        return fileService.findOne(Long.parseLong(id), false).getPath();
    }
}
